package es.uniovi.apuntesunioviapp.mocks.persistent;

import es.uniovi.apuntesunioviapp.mocks.MockCreator;
import es.uniovi.apuntesunioviapp.mocks.model.MockUser;
import es.uniovi.apuntesunioviapp.model.User;
import es.uniovi.apuntesunioviapp.repositories.AddressRepository;
import es.uniovi.apuntesunioviapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersistentUser implements MockCreator<User> {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public User create() {
        var user = new MockUser().create();
        user.setAddress(new PersistentAddress(addressRepository).create());
        return userRepository.save(user);
    }
}
