package es.uniovi.apuntesunioviapp.validators.impl;

import es.uniovi.apuntesunioviapp.model.User;
import es.uniovi.apuntesunioviapp.model.types.RoleType;
import es.uniovi.apuntesunioviapp.validators.Validator;
import lombok.AllArgsConstructor;

/**
 * Validate that a user has a specific role
 */
@AllArgsConstructor
public class ValidatorUserRole implements Validator {
    private User user;
    private RoleType roleType;

    @Override
    public boolean isValid() {
        return user != null && user.getRole() != null
            && user.getRole().equals(roleType);
    }
}
