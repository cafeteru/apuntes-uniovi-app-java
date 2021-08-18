package es.uniovi.apuntesunioviapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import es.uniovi.apuntesunioviapp.infrastructure.messages.TeachSubjectMessages;
import es.uniovi.apuntesunioviapp.model.types.RoleType;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents relationship between teachers and subjects
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class TeachSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User teacher;

    @ManyToOne
    private Subject subject;

    public void setTeacher(User teacher) {
        if (teacher == null || new ValidatorUserRole(teacher, RoleType.ROLE_TEACHER).isValid()) {
            this.teacher = teacher;
        } else {
            throw new IllegalArgumentException(TeachSubjectMessages.INVALID_TEACHER);
        }
    }
}
