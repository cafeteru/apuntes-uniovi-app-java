package es.uniovi.apuntesunioviapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.PartUnitSubjectLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.PartUnitSubjectMessages;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorMinValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents parts of the units of the subjects
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class PartUnitSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UnitSubject unitSubject;

    @Size(message = PartUnitSubjectMessages.LIMIT_NAME, max = PartUnitSubjectLimits.NAME)
    private String name;

    private int position;

    public void setPosition(int position) {
        var validator = new ValidatorMinValue<>(position, PartUnitSubjectLimits.MIN_POSITION);
        if (validator.isValid()) {
            this.position = position;
        } else {
            throw new IllegalArgumentException(PartUnitSubjectMessages.LIMIT_POSITION);
        }
    }
}
