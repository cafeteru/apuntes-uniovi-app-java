package es.uniovi.apuntesunioviapp.validators.impl;

import java.util.Locale;

import es.uniovi.apuntesunioviapp.validators.Validator;
import lombok.AllArgsConstructor;

/**
 * Validate that a text has the correct form of an NIE
 */
@AllArgsConstructor
public class ValidatorNie implements Validator {
    private String nie;

    @Override
    public boolean isValid() {
        if (nie == null) {
            return true;
        }
        if (nie.isEmpty()) {
            return false;
        }
        var aux = checkFirstLetter(nie.toUpperCase(), 'X', 0);
        aux = checkFirstLetter(aux, 'Y', 1);
        aux = checkFirstLetter(aux, 'Z', 2);
        return new ValidatorDni(aux).isValid();
    }

    private String checkFirstLetter(String nie, Character firstLetter, int numberLetter) {
        if (nie.charAt(0) == firstLetter) {
            return numberLetter + nie.substring(1);
        }
        return nie;
    }
}
