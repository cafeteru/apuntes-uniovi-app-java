package es.uniovi.apuntesunioviapp.dtos;

import java.time.LocalDate;

import es.uniovi.apuntesunioviapp.model.types.IdentificationType;
import es.uniovi.apuntesunioviapp.model.types.LanguageType;
import es.uniovi.apuntesunioviapp.model.types.RoleType;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private boolean active;
    private IdentificationType identificationType;
    private LanguageType language;
    private LocalDate birthDate;
    private RoleType role;
    private String email;
    private String img;
    private String name;
    private String numberIdentification;
    private String password;
    private String phone;
    private String surname;
    private String username;
}
