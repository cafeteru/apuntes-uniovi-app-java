package es.uniovi.apuntesunioviapp.repositories;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.uniovi.apuntesunioviapp.infrastructure.constants.UserLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages;
import es.uniovi.apuntesunioviapp.mocks.model.MockUser;
import es.uniovi.apuntesunioviapp.mocks.persistent.PersistentUser;
import es.uniovi.apuntesunioviapp.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void set_up() {
        user = new MockUser().create();
    }

    @Test
    void create_valid_data() {
        Assertions.assertNull(user.getId());
        user = userRepository.save(user);
        Assertions.assertNotNull(user.getId());
        var optional = userRepository.findById(user.getId());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), user);
    }

    @Test
    void create_with_address() {
        user = new PersistentUser(addressRepository, userRepository).create();
        Assertions.assertNotNull(user.getId());
        var optional = userRepository.findById(user.getId());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), user);
        Assertions.assertEquals(optional.get().getAddress(), user.getAddress());
    }

    @Test
    void null_username() {
        user.setUsername(null);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.NULL_USERNAME);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.NULL_USERNAME));
        }
    }

    @Test
    void empty_username() {
        user.setUsername("");
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.EMPTY_USERNAME);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.EMPTY_USERNAME));
        }
    }

    @Test
    void limit_username() {
        String username = "1".repeat(UserLimits.USERNAME);
        user.setUsername(username);
        user = userRepository.save(user);
        Assertions.assertEquals(user.getUsername(), username);
    }

    @Test
    void up_limit_username() {
        String username = "1".repeat(UserLimits.USERNAME + 1);
        user.setUsername(username);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.LIMIT_NAME);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.LIMIT_USERNAME));
        }
    }

    @Test
    void limit_email() {
        var size = UserLimits.EMAIL / 2;
        var end = ".es";
        String email = "1".repeat(size - 1) + "@" + "1".repeat(UserLimits.EMAIL - size - end.length()) + end;
        user.setEmail(email);
        user = userRepository.save(user);
        Assertions.assertEquals(user.getEmail(), email);
    }

    @Test
    void up_limit_email() {
        var aux = UserLimits.EMAIL / 2;
        String email = "1".repeat(aux) + "@" + "1".repeat(UserLimits.EMAIL - aux) + ".es";
        user.setEmail(email);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.LIMIT_EMAIL);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.LIMIT_EMAIL));
        }
    }

    @Test
    void limit_img() {
        String img = "1".repeat(UserLimits.IMG);
        user.setImg(img);
        user = userRepository.save(user);
        Assertions.assertEquals(user.getImg(), img);
    }

    @Test
    void up_limit_img() {
        String img = "1".repeat(UserLimits.IMG + 1);
        user.setImg(img);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.LIMIT_IMG);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.LIMIT_IMG));
        }
    }

    @Test
    void limit_name() {
        String name = "1".repeat(UserLimits.NAME);
        user.setName(name);
        user = userRepository.save(user);
        Assertions.assertEquals(user.getName(), name);
    }

    @Test
    void up_limit_name() {
        String name = "1".repeat(UserLimits.NAME + 1);
        user.setName(name);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.LIMIT_NAME);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.LIMIT_NAME));
        }
    }

    @Test
    void limit_surname() {
        String surname = "1".repeat(UserLimits.SURNAME);
        user.setSurname(surname);
        user = userRepository.save(user);
        Assertions.assertEquals(user.getSurname(), surname);
    }

    @Test
    void up_limit_surname() {
        String surname = "1".repeat(UserLimits.SURNAME + 1);
        user.setSurname(surname);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.LIMIT_SURNAME);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.LIMIT_SURNAME));
        }
    }

    @Test
    void null_password() {
        user.setPassword(null);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.NULL_PASSWORD);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.NULL_PASSWORD));
        }
    }

    @Test
    void empty_password() {
        user.setPassword("");
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.EMPTY_PASSWORD);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.EMPTY_PASSWORD));
        }
    }

    @Test
    void null_identification_type() {
        user.setIdentificationType(null);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.INVALID_IDENTIFICATION_TYPE);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.INVALID_IDENTIFICATION_TYPE));
        }
    }

    @Test
    void null_language() {
        user.setLanguage(null);
        try {
            userRepository.save(user);
            Assertions.fail(UserMessages.INVALID_LANGUAGE);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(UserMessages.INVALID_LANGUAGE));
        }
    }
}
