package hexlet.project;

import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapSchemaTest {

    private Validator v;
    private MapSchema schema;

    @BeforeEach
    void setUp() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    void testDefault() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    void testRequired() {
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    void testSizeof() {
        schema.sizeof(2);
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key1", "value1");
        assertThat(schema.isValid(data1)).isFalse();
        Map<String, Object> data2 = new HashMap<>();
        data2.put("key1", "value1");
        data2.put("key2", "value2");
        assertThat(schema.isValid(data2)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testRequiredWithSizeof() {
        schema.required().sizeof(2);
        assertThat(schema.isValid(null)).isFalse();
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key1", "value1");
        assertThat(schema.isValid(data1)).isFalse();
        Map<String, Object> data2 = new HashMap<>();
        data2.put("key1", "value1");
        data2.put("key2", "value2");
        assertThat(schema.isValid(data2)).isTrue();
        Map<String, Object> data3 = new HashMap<>();
        data3.put("key1", "value1");
        data3.put("key2", "value2");
        data3.put("key3", "value3");
        assertThat(schema.isValid(data3)).isFalse();
    }

    @Test
    void testShapeWithMissingKeys() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required());
        schema.shape(schemas);
        Map<String, Object> human = new HashMap<>();
        human.put("firstName", "John");
        assertThat(schema.isValid(human)).isFalse();
    }

    @Test
    void testShapeWithNullValues() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required());
        schema.shape(schemas);
        Map<String, Object> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", null);
        assertThat(schema.isValid(human)).isFalse();
    }
}
