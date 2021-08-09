package es.uniovi.apuntesunioviapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.apuntesunioviapp.model.Address;

/**
 * Manage the address table
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
