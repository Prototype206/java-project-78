
package hexlet.project;



import hexlet.code.Validator;

import hexlet.code.schemas.StringSchema;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class StringSchemaTest {

    @Test

    void testDummy() {

        Validator v = new Validator();

        StringSchema schema = v.string();

        assertThat(schema).isNotNull();

    }

}

