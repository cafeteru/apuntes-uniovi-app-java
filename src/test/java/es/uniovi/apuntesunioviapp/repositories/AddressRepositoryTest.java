package es.uniovi.apuntesunioviapp.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.uniovi.apuntesunioviapp.infrastructure.constants.AddressLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.AddressMessages;
import es.uniovi.apuntesunioviapp.mocks.model.MockAddress;
import es.uniovi.apuntesunioviapp.model.Address;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AddressRepositoryTest {

    private Address address;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void set_up() {
        address = new MockAddress().create();
    }

    @Test
    void create_valid() {
        assertNull(address.getId());
        address = addressRepository.save(address);
        assertNotNull(address.getId());
        var optional = addressRepository.findById(address.getId());
        assertTrue(optional.isPresent());
        assertEquals(optional.get(), address);
    }

    @Test
    void limit_city() {
        String city = "1".repeat(AddressLimits.CITY);
        address.setCity(city);
        address = addressRepository.save(address);
        Assertions.assertEquals(address.getCity(), city);
    }

    @Test
    void up_limit_city() {
        String city = "1".repeat(AddressLimits.CITY + 1);
        address.setCity(city);
        try {
            addressRepository.save(address);
            fail(AddressMessages.LIMIT_CITY);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(AddressMessages.LIMIT_CITY));
        }
    }

    @Test
    void limit_street() {
        String street = "1".repeat(AddressLimits.STREET);
        address.setStreet(street);
        address = addressRepository.save(address);
        assertEquals(address.getStreet(), street);
    }

    @Test
    void up_limit_street() {
        String street = "1".repeat(AddressLimits.STREET + 1);
        address.setStreet(street);
        try {
            addressRepository.save(address);
            fail(AddressMessages.LIMIT_STREET);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(AddressMessages.LIMIT_STREET));
        }
    }

    @Test
    void limit_postal_code() {
        String postalCode = "1".repeat(AddressLimits.POSTAL_CODE);
        address.setPostalCode(postalCode);
        address = addressRepository.save(address);
        assertEquals(address.getPostalCode(), postalCode);
    }

    @Test
    void up_limit_postal_code() {
        String postalCode = "1".repeat(AddressLimits.POSTAL_CODE + 1);
        address.setPostalCode(postalCode);
        try {
            addressRepository.save(address);
            fail(AddressMessages.LIMIT_POSTAL_CODE);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(AddressMessages.LIMIT_POSTAL_CODE));
        }
    }

    @Test
    void limit_country() {
        String country = "1".repeat(AddressLimits.COUNTRY);
        address.setCountry(country);
        address = addressRepository.save(address);
        assertEquals(address.getCountry(), country);
    }

    @Test
    void up_limit_country() {
        String country = "1".repeat(AddressLimits.COUNTRY + 1);
        address.setCountry(country);
        try {
            addressRepository.save(address);
            fail(AddressMessages.LIMIT_COUNTRY);
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains(AddressMessages.LIMIT_COUNTRY));
        }
    }
}
