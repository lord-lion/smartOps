package ifi.gestion.projet.smartOps.repositories;


import ifi.gestion.projet.smartOps.entities.Role;
import ifi.gestion.projet.smartOps.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(Authority name);
}
