package es.uniovi.apuntesunioviapp.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.AddressLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.AddressMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents addresses
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(message = AddressMessages.LIMIT_STREET, max = AddressLimits.STREET)
    private String street;

    @Size(message = AddressMessages.LIMIT_CITY, max = AddressLimits.CITY)
    private String city;

    @Size(message = AddressMessages.LIMIT_POSTAL_CODE, max = AddressLimits.POSTAL_CODE)
    private String postalCode;

    @Size(message = AddressMessages.LIMIT_COUNTRY, max = AddressLimits.COUNTRY)
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(postalCode, address.postalCode) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, postalCode, country);
    }
}
