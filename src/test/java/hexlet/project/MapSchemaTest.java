
package hexlet.project;



import hexlet.code.Validator;

import hexlet.code.schemas.MapSchema;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class MapSchemaTest {

    @Test

    void testDummy() {

        Validator v = new Validator();

        MapSchema schema = v.map();

        assertThat(schema).isNotNull();

    }

}

