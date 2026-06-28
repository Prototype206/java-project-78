package hexlet.project;

import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
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
}