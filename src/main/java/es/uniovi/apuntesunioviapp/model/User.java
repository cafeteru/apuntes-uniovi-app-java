package es.uniovi.apuntesunioviapp.model;

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

import es.uniovi.apuntesunioviapp.infrastructure.constants.UserLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages;
import es.uniovi.apuntesunioviapp.model.types.IdentificationType;
import es.uniovi.apuntesunioviapp.model.types.LanguageType;
import es.uniovi.apuntesunioviapp.model.types.RoleType;
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

    @Column(length = UserLimits.EMAIL)
    private String email;

    @Column(length = UserLimits.IMG)
    private String img;

    @Column(length = UserLimits.NAME)
    private String name;

    private String numberIdentification;

    @Column(length = UserLimits.PASSWORD)
    private String password;

    private String phone;

    @Column(length = UserLimits.SURNAME)
    private String surname;

    @NotNull(message = UserMessages.USERNAME_NULL)
    @NotEmpty(message = UserMessages.USERNAME_EMPTY)
    @Column(length = UserLimits.USERNAME)
    private String username;
}
