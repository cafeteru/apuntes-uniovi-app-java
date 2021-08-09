package es.uniovi.apuntesunioviapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.apuntesunioviapp.model.Address;
import es.uniovi.apuntesunioviapp.model.Subject;

/**
 * Manage the subject table
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
