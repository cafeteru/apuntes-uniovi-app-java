package es.uniovi.apuntesunioviapp.repositories;

import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.USERNAME_EMPTY;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.USERNAME_NULL;
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
import es.uniovi.apuntesunioviapp.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    private User user;

    @Autowired
    private UserRepository userRepository;

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
    void setUsernameNull() {
        user.setUsername(null);
        try {
            userRepository.save(user);
            fail(USERNAME_NULL);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(USERNAME_NULL));
        }
    }

    @Test
    void setUsernameEmpty() {
        user.setUsername("");
        try {
            userRepository.save(user);
            fail(USERNAME_EMPTY);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(USERNAME_EMPTY));
        }
    }
}
