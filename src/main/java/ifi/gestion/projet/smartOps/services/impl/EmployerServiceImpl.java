package ifi.gestion.projet.smartOps.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ifi.gestion.projet.smartOps.exceptions.ResourceNotFoundException;
import ifi.gestion.projet.smartOps.entities.Employer;
import ifi.gestion.projet.smartOps.repositories.EmployerRepository;
import ifi.gestion.projet.smartOps.service.EmployerService;
import lombok.RequiredArgsConstructor;
import ifi.gestion.projet.smartOps.repositories.RoleRepository;
import ifi.gestion.projet.smartOps.exceptions.AlreadyExistsException;
import ifi.gestion.projet.smartOps.exceptions.PasswordsNotMatchException; 
import ifi.gestion.projet.smartOps.entities.Role;
import ifi.gestion.projet.smartOps.entities.Status;
import ifi.gestion.projet.smartOps.entities.Authority;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService{
	private final EmployerRepository employerRepository;
    private final PasswordEncoder bcryptEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void register(Employer employer) throws AlreadyExistsException, PasswordsNotMatchException {
        if (Boolean.TRUE.equals(employerRepository.existsByEmail(employer.getEmail())))
            throw new AlreadyExistsException("This email already used");
        if (!employer.getPassword().equals(employer.getRePassword()))
            throw new PasswordsNotMatchException("The passwords don't match");

        employer.setPassword(bcryptEncoder.encode(employer.getPassword()));
        employer.setStatus(Status.ACTIVE);
        final HashSet<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(Authority.MEMBER);

        if (role == null) {
            role = Role.builder()
                    .name(Authority.MEMBER)
                    .build();
            roleRepository.save(role);
        }
        roles.add(role);
        employer.setRoles(roles);
        employerRepository.save(employer);
    }

    @Override
    public void edit(Employer employer) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Employer> employerOptional = employerRepository.findByEmail(email);
        if (employerOptional.isEmpty()) throw new ResourceNotFoundException("User not found");
        employerOptional.get().setFirstname(employer.getFirstname());
        employerOptional.get().setLastname(employer.getLastname());
        employerOptional.get().setDescription(employer.getDescription());
        employerOptional.get().setPhone(employer.getPhone());
        employerRepository.save(employerOptional.get());
    }


    @Override
    public Employer findByEmail(String email) {
        return employerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Member not found"));
    }

    @Override
    public List<Employer> getMembers() {
        return employerRepository.findAll();
    }

    @Override
    public Employer changeStatus(long id) {
        Employer employer = employerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        Status status = employer.getStatus() == Status.ACTIVE ? Status.INACTIVE : Status.ACTIVE;

        employer.setStatus(status);
        employerRepository.save(employer);

        System.out.println(status);
        System.out.println(employer.getStatus());
        return employer;
    }
}
