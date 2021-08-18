package es.uniovi.apuntesunioviapp.validators.impl;

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
            return value != null && limit != null
                && Double.parseDouble(value.toString()) <= Double.parseDouble(limit.toString());
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
