package es.uniovi.apuntesunioviapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.UnitSubjectLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.UnitSubjectMessages;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorMinValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents units of subjects
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UnitSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Subject subject;

    @Size(message = UnitSubjectMessages.LIMIT_NAME, max = UnitSubjectLimits.NAME)
    private String name;

    private int position;

    public void setPosition(int position) {
        var validator = new ValidatorMinValue<>(position, UnitSubjectLimits.MIN_POSITION);
        if (validator.isValid()) {
            this.position = position;
        } else {
            throw new IllegalArgumentException(UnitSubjectMessages.LIMIT_POSITION);
        }
    }
}
