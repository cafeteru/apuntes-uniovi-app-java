package es.uniovi.apuntesunioviapp.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import es.uniovi.apuntesunioviapp.validators.impl.ValidatorEmail;

class ValidatorEmailTest {

    @Test
    void validData() {
        var validator = new ValidatorEmail("email@email.es");
        assertTrue(validator.isValid());
    }

    @Test
    void nullData() {
        var validator = new ValidatorEmail(null);
        assertTrue(validator.isValid());
    }

    @Test
    void emptyData() {
        var validator = new ValidatorEmail("");
        assertFalse(validator.isValid());
    }

    @Test
    void invalidData() {
        var validator = new ValidatorEmail("@uniovi.es");
        assertFalse(validator.isValid());
        validator = new ValidatorEmail("uo239795@uniovi");
        assertFalse(validator.isValid());
        validator = new ValidatorEmail("uo239795@@uniovi");
        assertFalse(validator.isValid());
        validator = new ValidatorEmail("uo239795uniovi.es");
        assertFalse(validator.isValid());
        validator = new ValidatorEmail("uo239795uniovies");
        assertFalse(validator.isValid());
    }
}
