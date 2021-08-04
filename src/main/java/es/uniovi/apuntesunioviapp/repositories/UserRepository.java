package es.uniovi.apuntesunioviapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.apuntesunioviapp.model.User;

/**
 * Manage the users table
 */
interface UserRepository extends JpaRepository<User, Long> {
}
