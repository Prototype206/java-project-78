
package hexlet.project;



import hexlet.code.Validator;

import hexlet.code.schemas.NumberSchema;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class NumberSchemaTest {

    @Test

    void testDummy() {

        Validator v = new Validator();

        NumberSchema schema = v.number();

        assertThat(schema).isNotNull();

    }

}

