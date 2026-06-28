package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class NumberSchemaTest {

    private Validator v;
    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    void testDefault() {
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(-10)).isTrue();
        assertThat(schema.isValid(0)).isTrue();
    }

    @Test
    void testRequired() {
        schema.required();

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(-10)).isTrue();
        assertThat(schema.isValid(0)).isTrue();
    }

    @Test
    void testPositive() {
        schema.positive();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
    }

    @Test
    void testPositiveWithRequired() {
        schema.required().positive();

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
    }

    @Test
    void testRange() {
        schema.range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(7)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testRangeWithRequired() {
        schema.required().range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(7)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    void testComplexScenario() {
        schema.required().positive().range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(7)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
        assertThat(schema.isValid(-5)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    void testChaining() {
        NumberSchema schema1 = v.number().required().positive();
        assertThat(schema1.isValid(5)).isTrue();
        assertThat(schema1.isValid(-5)).isFalse();
        NumberSchema schema2 = v.number().range(1, 5).range(10, 20);
        assertThat(schema2.isValid(15)).isTrue();
        assertThat(schema2.isValid(3)).isFalse();
    }

    @Test
    void testPositiveWithNull() {
        NumberSchema schema = v.number().positive();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(-5)).isFalse();
    }

    @Test
    void testRangeWithNull() {
        NumberSchema schema = v.number().range(5, 10);
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(7)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void testChainingOverrides() {
        NumberSchema schema = v.number();
        schema.range(1, 5);
        assertThat(schema.isValid(3)).isTrue();
        assertThat(schema.isValid(10)).isFalse();
        schema.range(10, 20);
        assertThat(schema.isValid(3)).isFalse();
        assertThat(schema.isValid(15)).isTrue();
    }

    @Test
    void testPositiveWithoutRequired() {
        NumberSchema schema = v.number().positive();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(0)).isFalse();
    }
}