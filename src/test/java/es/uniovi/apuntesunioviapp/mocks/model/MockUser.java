package es.uniovi.apuntesunioviapp.mocks.model;

import java.time.LocalDate;

import es.uniovi.apuntesunioviapp.mocks.MockCreator;
import es.uniovi.apuntesunioviapp.mocks.RandomMethods;
import es.uniovi.apuntesunioviapp.model.User;
import es.uniovi.apuntesunioviapp.model.types.IdentificationType;
import es.uniovi.apuntesunioviapp.model.types.LanguageType;
import es.uniovi.apuntesunioviapp.model.types.RoleType;

/**
 * Create a mock user to test
 */
public class MockUser implements MockCreator<User> {

    @Override
    public User create() {
        String username = RandomMethods.randomUsername();
        return User.builder()
            .active(true)
            .birthDate(LocalDate.now())
            .email("email@email.es")
            .identificationType(IdentificationType.DNI)
            .img("img")
            .language(LanguageType.ES)
            .name("Name")
            .password(username)
            .phone("698523147")
            .role(RoleType.ADMIN)
            .surname("Surname")
            .username(username)
            .build();
    }
}
