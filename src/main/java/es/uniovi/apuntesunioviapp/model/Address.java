package es.uniovi.apuntesunioviapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import es.uniovi.apuntesunioviapp.infrastructure.constants.AddressLimits;
import es.uniovi.apuntesunioviapp.infrastructure.messages.AddressMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents addresses
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
