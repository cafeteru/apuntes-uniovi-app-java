package es.uniovi.apuntesunioviapp.validators.impl;

import java.time.LocalDate;

import es.uniovi.apuntesunioviapp.validators.Validator;
import lombok.AllArgsConstructor;

/**
 * Validate that a date isn´t later than the current day
 */
@AllArgsConstructor
public class ValidatorIsBeforeToday implements Validator {
    private LocalDate date;

    @Override
    public boolean isValid() {
        var limit = LocalDate.now();
        if (date != null) {
            return date.isBefore(limit) || date.isEqual(limit);
        }
        return true;
    }
}
