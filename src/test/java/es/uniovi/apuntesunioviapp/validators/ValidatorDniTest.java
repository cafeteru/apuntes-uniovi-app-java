package es.uniovi.apuntesunioviapp.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.uniovi.apuntesunioviapp.mocks.RandomMethods;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorDni;

/**
 * Check class ValidatorDni
 */
class ValidatorDniTest {

    @Test
    void valid_data() {
        var validator = new ValidatorDni(RandomMethods.dni());
        Assertions.assertTrue(validator.isValid());
    }

    @Test
    void null_data() {
        var validator = new ValidatorDni(null);
        Assertions.assertTrue(validator.isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "6069263B",
        "",
        "60692Z63B",
        "584654383",
        "58465438S"
    })
    void invalid_data(String dni) {
        var validator = new ValidatorDni(dni);
        Assertions.assertFalse(validator.isValid());
    }
}
