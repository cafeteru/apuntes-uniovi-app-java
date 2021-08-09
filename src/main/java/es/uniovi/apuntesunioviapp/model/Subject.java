package es.uniovi.apuntesunioviapp.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.SubjectLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.SubjectMessages;
import es.uniovi.apuntesunioviapp.model.types.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents subjects
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
