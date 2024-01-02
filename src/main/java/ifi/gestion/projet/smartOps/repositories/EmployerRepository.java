package ifi.gestion.projet.smartOps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ifi.gestion.projet.smartOps.entities.Employer;
import ifi.gestion.projet.smartOps.exceptions.AlreadyExistsException;


@SuppressWarnings("unused")
public interface EmployerRepository extends JpaRepository<Employer, Long>{
	Optional<Employer> findByEmail(String email);
	   // Optional<Member> findByEmailAndActiveTrue(String email);
	    Boolean existsByEmail(String email);
}
