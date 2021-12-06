package es.uniovi.apuntesunioviapp.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.uniovi.apuntesunioviapp.infrastructure.constants.SubjectLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.SubjectMessages;
import es.uniovi.apuntesunioviapp.mocks.model.MockSubject;
import es.uniovi.apuntesunioviapp.model.Subject;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SubjectRepositoryTest {

    private Subject subject;

    @Autowired
    private SubjectRepository subjectRepository;

    @BeforeEach
    void set_up() {
        subject = new MockSubject().create();
    }

    @Test
    void create_valid_data() {
        assertNull(subject.getId());
        subject = subjectRepository.save(subject);
        assertNotNull(subject.getId());
        var optional = subjectRepository.findById(subject.getId());
        assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), subject);
    }

    @Test
    void limit_name() {
        String name = "1".repeat(SubjectLimits.NAME);
        subject.setName(name);
        subject = subjectRepository.save(subject);
        Assertions.assertEquals(subject.getName(), name);
    }

    @Test
    void up_limit_name() {
        String name = "1".repeat(SubjectLimits.NAME + 1);
        subject.setName(name);
        try {
            subjectRepository.save(subject);
            fail(SubjectMessages.LIMIT_NAME);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(SubjectMessages.LIMIT_NAME));
        }
    }

    @Test
    void null_subject_type() {
        subject.setSubjectType(null);
        try {
            subject = subjectRepository.save(subject);
            fail(SubjectMessages.INVALID_SUBJECT_TYPE);
        } catch (ConstraintViolationException e) {
            Assertions.assertTrue(e.getMessage().contains(SubjectMessages.INVALID_SUBJECT_TYPE));
        }
    }
}
