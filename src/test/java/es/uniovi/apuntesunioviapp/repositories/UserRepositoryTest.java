package es.uniovi.apuntesunioviapp.repositories;

import static es.uniovi.apuntesunioviapp.infrastructure.constants.UserLimits.EMAIL;
import static es.uniovi.apuntesunioviapp.infrastructure.constants.UserLimits.IMG;
import static es.uniovi.apuntesunioviapp.infrastructure.constants.UserLimits.NAME;
import static es.uniovi.apuntesunioviapp.infrastructure.constants.UserLimits.USERNAME;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.EMPTY_PASSWORD;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.EMPTY_USERNAME;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.LIMIT_EMAIL;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.LIMIT_IMG;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.LIMIT_NAME;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.LIMIT_USERNAME;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.NULL_PASSWORD;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.NULL_USERNAME;
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
        user = new MockUser().createWithoutId();
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
            fail(NULL_USERNAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(NULL_USERNAME));
        }
    }

    @Test
    void emptyUsername() {
        user.setUsername("");
        try {
            userRepository.save(user);
            fail(EMPTY_USERNAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(EMPTY_USERNAME));
        }
    }

    @Test
    void limitUsername() {
        String username = "1".repeat(USERNAME);
        user.setUsername(username);
        user = userRepository.save(user);
        assertEquals(user.getUsername(), username);
    }

    @Test
    void upLimitUsername() {
        String username = "1".repeat(USERNAME + 1);
        user.setUsername(username);
        try {
            userRepository.save(user);
            fail(LIMIT_NAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(LIMIT_USERNAME));
        }
    }

    @Test
    void limitEmail() {
        var size = EMAIL / 2;
        var end = ".es";
        String email = "1".repeat(size - 1) +
            "@" +
            "1".repeat(EMAIL - size - end.length()) + end;
        user.setEmail(email);
        user = userRepository.save(user);
        assertEquals(user.getEmail(), email);
    }

    @Test
    void upLimitEmail() {
        var aux = EMAIL / 2;
        String email = "1".repeat(aux) +
            "@" +
            "1".repeat(EMAIL - aux) +
            ".es";
        user.setEmail(email);
        try {
            userRepository.save(user);
            fail(LIMIT_EMAIL);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(LIMIT_EMAIL));
        }
    }

    @Test
    void limitImg() {
        String img = "1".repeat(IMG);
        user.setImg(img);
        user = userRepository.save(user);
        assertEquals(user.getImg(), img);
    }

    @Test
    void upLimitImg() {
        String img = "1".repeat(IMG + 1);
        user.setImg(img);
        try {
            userRepository.save(user);
            fail(LIMIT_IMG);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(LIMIT_IMG));
        }
    }

    @Test
    void limitName() {
        String name = "1".repeat(NAME);
        user.setName(name);
        user = userRepository.save(user);
        assertEquals(user.getName(), name);
    }

    @Test
    void upLimitName() {
        String name = "1".repeat(NAME + 1);
        user.setName(name);
        try {
            userRepository.save(user);
            fail(LIMIT_NAME);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(LIMIT_NAME));
        }
    }

    @Test
    void nullPassword() {
        user.setPassword(null);
        try {
            userRepository.save(user);
            fail(NULL_PASSWORD);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(NULL_PASSWORD));
        }
    }

    @Test
    void emptyPassword() {
        user.setPassword("");
        try {
            userRepository.save(user);
            fail(EMPTY_PASSWORD);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(EMPTY_PASSWORD));
        }
    }
}
