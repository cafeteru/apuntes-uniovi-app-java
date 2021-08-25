package es.uniovi.apuntesunioviapp.validators.impl;

import java.util.Objects;
import java.util.regex.Pattern;

import es.uniovi.apuntesunioviapp.validators.Validator;
import lombok.AllArgsConstructor;

/**
 * Validate that a text has the correct form of an email
 */
@AllArgsConstructor
public class ValidatorEmail implements Validator {
    private String email;

    @Override
    public boolean isValid() {
        if (Objects.nonNull(email)) {
            var pattern = Pattern.compile(
                "^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            );
            var mather = pattern.matcher(email);
            return mather.find();
        }
        return true;
    }
}
