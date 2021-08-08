package es.uniovi.apuntesunioviapp.validators;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import es.uniovi.apuntesunioviapp.validators.impl.ValidatorIsBeforeToday;

/**
 * Check class ValidatorLaterDayToday
 */
class ValidatorIsBeforeTodayTest {

    @Test
    void validData() {
        var date = LocalDate.now();
        var validator = new ValidatorIsBeforeToday(date);
        assertTrue(validator.isValid());
        date = LocalDate.now().minusDays(1);
        validator = new ValidatorIsBeforeToday(date);
        assertTrue(validator.isValid());
    }

    @Test
    void nullData() {
        var validator = new ValidatorIsBeforeToday(null);
        assertTrue(validator.isValid());
    }

    @Test
    void invalidData() {
        var date = LocalDate.now().plusDays(1);
        var validator = new ValidatorIsBeforeToday(date);
        Assertions.assertFalse(validator.isValid());
    }
}
