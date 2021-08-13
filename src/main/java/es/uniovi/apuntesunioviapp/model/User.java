package es.uniovi.apuntesunioviapp.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Represents users
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    @Enumerated(EnumType.STRING)
    @NotNull(message = UserMessages.INVALID_IDENTIFICATION_TYPE)
    private IdentificationType identificationType;

    @Enumerated(EnumType.STRING)
    @NotNull(message = UserMessages.INVALID_LANGUAGE)
    private LanguageType language;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = UserMessages.INVALID_ROLE_TYPE)
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

    @Size(message = UserMessages.LIMIT_SURNAME, max = UserLimits.SURNAME)
    private String surname;

    @NotNull(message = UserMessages.NULL_USERNAME)
    @NotEmpty(message = UserMessages.EMPTY_USERNAME)
    @Size(message = UserMessages.LIMIT_USERNAME, max = UserLimits.USERNAME)
    private String username;

    @OneToOne
    private Address address;

    public void setEmail(String email) {
        if (new ValidatorEmail(email).isValid()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException(UserMessages.INVALID_EMAIL);
        }
    }

    public void setBirthDate(LocalDate birthDate) {
        if (new ValidatorIsBeforeToday(birthDate).isValid()) {
            this.birthDate = birthDate;
        } else {
            throw new IllegalArgumentException(UserMessages.LIMIT_BIRTH_DATE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
