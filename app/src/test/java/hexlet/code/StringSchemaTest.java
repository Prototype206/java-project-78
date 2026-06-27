package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {

    private Validator v;
    private StringSchema schema;

    @BeforeEach
    void setUp() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    void testRequired() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid("hello")).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("hello")).isTrue();
    }

    @Test
    void testMinLength() {
        schema.minLength(5);

        assertThat(schema.isValid("hello")).isTrue();
        assertThat(schema.isValid("hi")).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testContains() {
        schema.contains("fox");

        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hello")).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testMultipleRules() {
        schema.required()
               .minLength(5)
               .contains("hex");

        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid("hex")).isFalse();
        assertThat(schema.isValid("hello hex")).isTrue();
        assertThat(schema.isValid("hello")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
    }
}