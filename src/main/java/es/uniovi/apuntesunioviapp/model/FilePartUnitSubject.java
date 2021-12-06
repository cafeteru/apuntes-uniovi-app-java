package es.uniovi.apuntesunioviapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.FilePartUnitSubjectLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.FilePartUnitSubjectMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents files of the unit of the subjects
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class FilePartUnitSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(message = FilePartUnitSubjectMessages.LIMIT_CONTENT, max = FilePartUnitSubjectLimits.CONTENT)
    private String content;

    @ManyToOne
    private PartUnitSubject partUnitSubject;
}
