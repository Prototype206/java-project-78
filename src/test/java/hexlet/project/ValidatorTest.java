package hexlet.project;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private Validator v;
    private MapSchema schema;

    @BeforeEach
    void setUp() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    void testShapeWithStrings() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertThat(schema.isValid(human2)).isFalse();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("firstName", "John");
        assertThat(schema.isValid(human4)).isFalse();
    }

    @Test
    void testShapeWithNumbers() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("age", v.number().required().positive().range(18, 100));
        schemas.put("score", v.number().required().range(0, 10));

        schema.shape(schemas);

        Map<String, Object> data1 = new HashMap<>();
        data1.put("age", 25);
        data1.put("score", 8);
        assertThat(schema.isValid(data1)).isTrue();

        Map<String, Object> data2 = new HashMap<>();
        data2.put("age", 17);
        data2.put("score", 8);
        assertThat(schema.isValid(data2)).isFalse();

        Map<String, Object> data3 = new HashMap<>();
        data3.put("age", 25);
        data3.put("score", 11);
        assertThat(schema.isValid(data3)).isFalse();
    }

    @Test
    void testShapeWithMixedTypes() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", v.string().required().minLength(3));
        schemas.put("age", v.number().required().positive());
        schemas.put("email", v.string().required().contains("@"));

        schema.shape(schemas);

        Map<String, Object> user1 = new HashMap<>();
        user1.put("name", "John");
        user1.put("age", 30);
        user1.put("email", "john@example.com");
        assertThat(schema.isValid(user1)).isTrue();

        Map<String, Object> user2 = new HashMap<>();
        user2.put("name", "John");
        user2.put("age", 30);
        user2.put("email", "johnexample.com");
        assertThat(schema.isValid(user2)).isFalse();
    }

    @Test
    void testShapeWithRequiredAndSizeof() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", v.string().required().minLength(3));
        schemas.put("age", v.number().required().positive());

        schema.required().sizeof(2).shape(schemas);

        Map<String, Object> user1 = new HashMap<>();
        user1.put("name", "John");
        user1.put("age", 30);
        assertThat(schema.isValid(user1)).isTrue();

        Map<String, Object> user2 = new HashMap<>();
        user2.put("name", "John");
        user2.put("age", 30);
        user2.put("email", "john@example.com");
        assertThat(schema.isValid(user2)).isFalse();

        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    void testComplexMap() {
        Map<String, Object> actual1 = new HashMap<>();
        actual1.put("key", "value");
        assertThat(schema.isValid(actual1)).isTrue();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("key", v.string().required().minLength(3));
        schema.shape(schemas);

        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("key", "hello");
        assertThat(schema.isValid(actual2)).isTrue();

        Map<String, Object> actual3 = new HashMap<>();
        actual3.put("key", "hi");
        assertThat(schema.isValid(actual3)).isFalse();

        Map<String, Object> actual4 = new HashMap<>();
        assertThat(schema.isValid(actual4)).isFalse();
    }

    @Test
    void testShapeWithNestedMaps() {
        Map<String, BaseSchema<?>> innerSchemas = new HashMap<>();
        innerSchemas.put("street", v.string().required().minLength(3));
        innerSchemas.put("city", v.string().required());

        Map<String, BaseSchema<?>> outerSchemas = new HashMap<>();
        outerSchemas.put("name", v.string().required());
        outerSchemas.put("address", v.map().required().shape(innerSchemas));

        schema.required().shape(outerSchemas);

        Map<String, Object> address = new HashMap<>();
        address.put("street", "Main St");
        address.put("city", "New York");

        Map<String, Object> person = new HashMap<>();
        person.put("name", "John");
        person.put("address", address);

        assertThat(schema.isValid(person)).isTrue();

        Map<String, Object> invalidAddress = new HashMap<>();
        invalidAddress.put("street", "A");
        invalidAddress.put("city", "Boston");

        Map<String, Object> invalidPerson = new HashMap<>();
        invalidPerson.put("name", "Jane");
        invalidPerson.put("address", invalidAddress);

        assertThat(schema.isValid(invalidPerson)).isFalse();
    }

    @Test
    void testShapeWithCustomRules() {
        StringSchema nameSchema = v.string().required().minLength(2).contains("A");
        NumberSchema idSchema = v.number().required().positive();

        assertThat(nameSchema.isValid("Alice")).isTrue();
        assertThat(nameSchema.isValid("Bob")).isFalse();
        assertThat(idSchema.isValid(123)).isTrue();
        assertThat(idSchema.isValid(-5)).isFalse();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("id", idSchema);
        schemas.put("name", nameSchema);

        schema.shape(schemas);

        Map<String, Object> valid = new HashMap<>();
        valid.put("id", 123);
        valid.put("name", "Alice");

        boolean result = schema.isValid(valid);
        assertThat(result).isTrue();

        Map<String, Object> invalid = new HashMap<>();
        invalid.put("id", -5);
        invalid.put("name", "Bob");
        assertThat(schema.isValid(invalid)).isFalse();
    }
}
