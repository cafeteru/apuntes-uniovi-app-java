package es.uniovi.apuntesunioviapp.mocks.persistent;

import es.uniovi.apuntesunioviapp.mocks.MockCreator;
import es.uniovi.apuntesunioviapp.mocks.model.MockAddress;
import es.uniovi.apuntesunioviapp.model.Address;
import es.uniovi.apuntesunioviapp.repositories.AddressRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersistentAddress implements MockCreator<Address> {
    private final AddressRepository addressRepository;

    @Override
    public Address create() {
        var address = new MockAddress().create();
        return addressRepository.save(address);
    }
}
