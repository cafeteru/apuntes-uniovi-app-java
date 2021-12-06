package es.uniovi.apuntesunioviapp.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import es.uniovi.apuntesunioviapp.infrastructure.messages.LearnSubjectMessages;
import es.uniovi.apuntesunioviapp.model.types.RoleType;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents relationship between students and subjects
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class LearnSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Subject subject;

    public void setStudent(User student) {
        if (Objects.isNull(student) || new ValidatorUserRole(student, RoleType.STUDENT).isValid()) {
            this.student = student;
        } else {
            throw new IllegalArgumentException(LearnSubjectMessages.INVALID_STUDENT);
        }
    }
}
