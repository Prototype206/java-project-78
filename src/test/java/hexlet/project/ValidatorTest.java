
package hexlet.project;



import hexlet.code.Validator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class ValidatorTest {

    @Test

    void testDummy() {

        Validator v = new Validator();

        assertThat(v).isNotNull();

    }

}

