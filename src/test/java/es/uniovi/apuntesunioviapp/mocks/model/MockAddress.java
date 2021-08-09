package es.uniovi.apuntesunioviapp.mocks.model;

import es.uniovi.apuntesunioviapp.mocks.MockCreator;
import es.uniovi.apuntesunioviapp.model.Address;

/**
 * Create a mock address to test
 */
public class MockAddress implements MockCreator<Address> {

    @Override
    public Address create() {
        return Address.builder()
            .city("city")
            .country("country")
            .postalCode("32458")
            .street("street")
            .build();
    }
}
