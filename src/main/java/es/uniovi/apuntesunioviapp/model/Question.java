package es.uniovi.apuntesunioviapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.QuestionLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.QuestionMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents questions of the tests of the subjects
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Test test;

    @Size(message = QuestionMessages.LIMIT_STATEMENT, max = QuestionLimits.STATEMENT)
    private String statement;

    private Double points;
}
