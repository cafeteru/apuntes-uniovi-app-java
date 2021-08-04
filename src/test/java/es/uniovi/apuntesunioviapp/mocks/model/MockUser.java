package es.uniovi.apuntesunioviapp.mocks.model;

import java.time.LocalDate;

import es.uniovi.apuntesunioviapp.mocks.MockCreator;
import es.uniovi.apuntesunioviapp.mocks.RandomMethods;
import es.uniovi.apuntesunioviapp.model.User;
import es.uniovi.apuntesunioviapp.model.types.IdentificationType;
import es.uniovi.apuntesunioviapp.model.types.RoleType;

public class MockUser implements MockCreator<User> {

    @Override
    public User create() {
        String username = RandomMethods.randomUsername();
        return User.builder()
            .active(true)
            .birthDate(LocalDate.now())
            .email("email@email.es")
            .id(1L)
            .identificationType(IdentificationType.DNI)
            .img("img")
            .name("Name")
            .password(username)
            .phone("698523147")
            .role(RoleType.ROLE_ADMIN)
            .surname("Surname")
            .username(username)
            .build();
    }

    public User createWithoutId() {
        var user = create();
        user.setId(null);
        return user;
    }
}
