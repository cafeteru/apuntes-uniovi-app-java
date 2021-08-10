package es.uniovi.apuntesunioviapp.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javax.validation.ConstraintViolationException;

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
    void setUp() {
        user = new MockUser().create();
    }

    @Test
    void createValidData() {
        assertNull(user.getId());
        user = userRepository.save(user);
        assertNotNull(user.getId());
        var optional = userRepository.findById(user.getId());
        assertTrue(optional.isPresent());
        assertEquals(optional.get(), user);
    }

    @Test
    void createWithAddress() {
        user = new PersistentUser(addressRepository, userRepository).create();
        assertNotNull(user.getId());
        var optional = userRepository.findById(user.getId());
        assertTrue(optional.isPresent());
        assertEquals(optional.get(), user);
        assertEquals(optional.get().getAddress(), user.getAddress());
    }

    @Test
    void nullUsername() {
        user.setUsername(null);
        try {
            userRepository.save(user);
            fail(UserMessages.NULL_USERNAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.NULL_USERNAME));
        }
    }

    @Test
    void emptyUsername() {
        user.setUsername("");
        try {
            userRepository.save(user);
            fail(UserMessages.EMPTY_USERNAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.EMPTY_USERNAME));
        }
    }

    @Test
    void limitUsername() {
        String username = "1".repeat(UserLimits.USERNAME);
        user.setUsername(username);
        user = userRepository.save(user);
        assertEquals(user.getUsername(), username);
    }

    @Test
    void upLimitUsername() {
        String username = "1".repeat(UserLimits.USERNAME + 1);
        user.setUsername(username);
        try {
            userRepository.save(user);
            fail(UserMessages.LIMIT_NAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.LIMIT_USERNAME));
        }
    }

    @Test
    void limitEmail() {
        var size = UserLimits.EMAIL / 2;
        var end = ".es";
        String email = "1".repeat(size - 1) +
            "@" +
            "1".repeat(UserLimits.EMAIL - size - end.length()) + end;
        user.setEmail(email);
        user = userRepository.save(user);
        assertEquals(user.getEmail(), email);
    }

    @Test
    void upLimitEmail() {
        var aux = UserLimits.EMAIL / 2;
        String email = "1".repeat(aux) +
            "@" +
            "1".repeat(UserLimits.EMAIL - aux) +
            ".es";
        user.setEmail(email);
        try {
            userRepository.save(user);
            fail(UserMessages.LIMIT_EMAIL);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.LIMIT_EMAIL));
        }
    }

    @Test
    void limitImg() {
        String img = "1".repeat(UserLimits.IMG);
        user.setImg(img);
        user = userRepository.save(user);
        assertEquals(user.getImg(), img);
    }

    @Test
    void upLimitImg() {
        String img = "1".repeat(UserLimits.IMG + 1);
        user.setImg(img);
        try {
            userRepository.save(user);
            fail(UserMessages.LIMIT_IMG);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.LIMIT_IMG));
        }
    }

    @Test
    void limitName() {
        String name = "1".repeat(UserLimits.NAME);
        user.setName(name);
        user = userRepository.save(user);
        assertEquals(user.getName(), name);
    }

    @Test
    void upLimitName() {
        String name = "1".repeat(UserLimits.NAME + 1);
        user.setName(name);
        try {
            userRepository.save(user);
            fail(UserMessages.LIMIT_NAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.LIMIT_NAME));
        }
    }

    @Test
    void limitSurname() {
        String surname = "1".repeat(UserLimits.SURNAME);
        user.setSurname(surname);
        user = userRepository.save(user);
        assertEquals(user.getSurname(), surname);
    }

    @Test
    void upLimitSurname() {
        String surname = "1".repeat(UserLimits.SURNAME + 1);
        user.setSurname(surname);
        try {
            userRepository.save(user);
            fail(UserMessages.LIMIT_SURNAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.LIMIT_SURNAME));
        }
    }

    @Test
    void nullPassword() {
        user.setPassword(null);
        try {
            userRepository.save(user);
            fail(UserMessages.NULL_PASSWORD);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.NULL_PASSWORD));
        }
    }

    @Test
    void emptyPassword() {
        user.setPassword("");
        try {
            userRepository.save(user);
            fail(UserMessages.EMPTY_PASSWORD);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.EMPTY_PASSWORD));
        }
    }

    @Test
    void nullIdentificationType() {
        user.setIdentificationType(null);
        try {
            userRepository.save(user);
            fail(UserMessages.INVALID_IDENTIFICATION_TYPE);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.INVALID_IDENTIFICATION_TYPE));
        }
    }

    @Test
    void nullLanguage() {
        user.setLanguage(null);
        try {
            userRepository.save(user);
            fail(UserMessages.INVALID_LANGUAGE);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(UserMessages.INVALID_LANGUAGE));
        }
    }
}
