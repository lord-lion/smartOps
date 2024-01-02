package ifi.gestion.projet.smartOps.service;

import java.util.List;

import ifi.gestion.projet.smartOps.entities.Employer;
import ifi.gestion.projet.smartOps.exceptions.AlreadyExistsException;
import ifi.gestion.projet.smartOps.exceptions.PasswordsNotMatchException;

public interface EmployerService {
	void register(Employer content) throws AlreadyExistsException, PasswordsNotMatchException;
    void edit(Employer content);
    Employer findByEmail(String username);
    List<Employer> getMembers();
    Employer changeStatus(long id);
}
