package es.uniovi.apuntesunioviapp.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.SubjectLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.SubjectMessages;
import es.uniovi.apuntesunioviapp.model.types.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents subjects
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(message = SubjectMessages.LIMIT_NAME, max = SubjectLimits.NAME)
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = SubjectMessages.INVALID_SUBJECT_TYPE)
    private SubjectType subjectType;

    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name) && subjectType == subject.subjectType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subjectType);
    }
}
