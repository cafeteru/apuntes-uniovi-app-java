package es.uniovi.apuntesunioviapp.model;

import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.INVALID_EMAIL;
import static es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages.LIMIT_BIRTH_DATE;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.UserLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages;
import es.uniovi.apuntesunioviapp.model.types.IdentificationType;
import es.uniovi.apuntesunioviapp.model.types.LanguageType;
import es.uniovi.apuntesunioviapp.model.types.RoleType;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorEmail;
import es.uniovi.apuntesunioviapp.validators.impl.ValidatorIsBeforeToday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents users
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Size(message = UserMessages.LIMIT_EMAIL, max = UserLimits.EMAIL)
    private String email;

    @Size(message = UserMessages.LIMIT_IMG, max = UserLimits.IMG)
    private String img;

    @Size(message = UserMessages.LIMIT_NAME, max = UserLimits.NAME)
    private String name;

    private String numberIdentification;

    @NotNull(message = UserMessages.NULL_PASSWORD)
    @NotEmpty(message = UserMessages.EMPTY_PASSWORD)
    @Column(length = UserLimits.PASSWORD)
    private String password;

    private String phone;

    @Column(length = UserLimits.SURNAME)
    private String surname;

    @NotNull(message = UserMessages.NULL_USERNAME)
    @NotEmpty(message = UserMessages.EMPTY_USERNAME)
    @Size(message = UserMessages.LIMIT_USERNAME, max = UserLimits.USERNAME)
    private String username;

    public void setEmail(String email) {
        if (new ValidatorEmail(email).isValid()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException(INVALID_EMAIL);
        }
    }

    public void setBirthDate(LocalDate birthDate) {
        if (new ValidatorIsBeforeToday(birthDate).isValid()) {
            this.birthDate = birthDate;
        } else {
            throw new IllegalArgumentException(LIMIT_BIRTH_DATE);
        }
    }
}
