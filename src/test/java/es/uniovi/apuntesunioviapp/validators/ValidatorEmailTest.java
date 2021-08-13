package es.uniovi.apuntesunioviapp.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.uniovi.apuntesunioviapp.validators.impl.ValidatorEmail;

class ValidatorEmailTest {

    @Test
    void valid_data() {
        var validator = new ValidatorEmail("email@email.es");
        assertTrue(validator.isValid());
    }

    @Test
    void null_data() {
        var validator = new ValidatorEmail(null);
        assertTrue(validator.isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {
    	"",
        "@uniovi.es",
        "uo239795@uniovi",
        "uo239795@@uniovi",
        "uo239795uniovi.es",
        "uo239795uniovies"
    })
    void invalidData(String email) {
        var validator = new ValidatorEmail(email);
        assertFalse(validator.isValid());
    }
}
