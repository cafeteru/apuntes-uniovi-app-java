package es.uniovi.apuntesunioviapp.validators.impl;

import java.util.Objects;

import es.uniovi.apuntesunioviapp.validators.Validator;
import lombok.AllArgsConstructor;

/**
 * Validate that a value is greater than a limit
 */
@AllArgsConstructor
public class ValidatorMinValue<T> implements Validator {
    private T value;
    private T limit;

    @Override
    public boolean isValid() {
        try {
            return Objects.nonNull(value) && Objects.nonNull(limit)
                && Double.parseDouble(value.toString()) <= Double.parseDouble(limit.toString());
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
