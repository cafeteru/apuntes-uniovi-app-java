package es.uniovi.apuntesunioviapp.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages;
import es.uniovi.apuntesunioviapp.mocks.model.MockUser;

/**
 * Check class User
 */
class UserTest {
    private User user;

    @BeforeEach
    void set_up() {
        user = new MockUser().create();
    }

    @Test
    void valid_email() {
        var email = "email@email.es";
        user.setEmail(email);
        Assertions.assertEquals(email, user.getEmail());
    }

    @Test
    void invalid_email() {
        var email = "emailemail.es";
        try {
            user.setEmail(email);
            Assertions.fail(UserMessages.INVALID_EMAIL);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.INVALID_EMAIL));
        }
    }

    @Test
    void valid_birth_date() {
        var localDate = LocalDate.now();
        user.setBirthDate(localDate);
        Assertions.assertEquals(localDate, user.getBirthDate());
    }

    @Test
    void invalid_birth_date() {
        var localDate = LocalDate.now().plusDays(1);
        try {
            user.setBirthDate(localDate);
            Assertions.fail(UserMessages.LIMIT_BIRTH_DATE);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.LIMIT_BIRTH_DATE));
        }
    }
}
