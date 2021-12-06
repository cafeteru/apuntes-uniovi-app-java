package es.uniovi.apuntesunioviapp.validators;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import es.uniovi.apuntesunioviapp.model.User;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorMinValue;

/**
 * Check class ValidatorMinValue
 */
class ValidatorMinValueTest {

    @ParameterizedTest
    @CsvSource({
        "1, 2",
        "1, 1"
    })
    void valid_integer_data(int value, int limit) {
        var validator = new ValidatorMinValue<>(value, limit);
        Assertions.assertTrue(validator.isValid());
    }

    @ParameterizedTest
    @MethodSource("provideNullParams")
    void null_data(Long value, Long limit) {
        var validator = new ValidatorMinValue<>(value, limit);
        Assertions.assertFalse(validator.isValid());
    }

    private static Stream<Arguments> provideNullParams() {
        return Stream.of(
            Arguments.of(null, 2L),
            Arguments.of(1L, null)
        );
    }

    @ParameterizedTest
    @CsvSource({
        "3, 2"
    })
    void invalid_integer_data(int value, int limit) {
        var validator = new ValidatorMinValue<>(value, limit);
        Assertions.assertFalse(validator.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2",
        "1, 1"
    })
    void valid_long_data(long value, long limit) {
        var validator = new ValidatorMinValue<>(value, limit);
        Assertions.assertTrue(validator.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "3, 2"
    })
    void invalid_long_data(long value, long limit) {
        var validator = new ValidatorMinValue<>(value, limit);
        Assertions.assertFalse(validator.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1.0, 2.0",
        "1.0, 1.0"
    })
    void valid_double_data(double value, double limit) {
        var validator = new ValidatorMinValue<>(value, limit);
        Assertions.assertTrue(validator.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "3.0, 2.0"
    })
    void invalid_double_data(double value, double limit) {
        var validator = new ValidatorMinValue<>(value, limit);
        Assertions.assertFalse(validator.isValid());
    }

    @Test
    void no_number_type() {
        var validator = new ValidatorMinValue<>(new User(), new User());
        Assertions.assertFalse(validator.isValid());
    }
}
