package es.uniovi.apuntesunioviapp.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.uniovi.apuntesunioviapp.mocks.RandomMethods;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorNie;

/**
 * Check class ValidatorDni
 */
class ValidatorNieTest {

    @Test
    void valid_data() {
        var validator = new ValidatorNie(RandomMethods.nie());
        Assertions.assertTrue(validator.isValid());
    }

    @Test
    void null_data() {
        var validator = new ValidatorNie(null);
        Assertions.assertTrue(validator.isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Y050166T",
        "",
        "X0501662",
        "1050Y662T"
    })
    void invalid_data(String nie) {
        var validator = new ValidatorNie(nie);
        Assertions.assertFalse(validator.isValid());
    }
}
