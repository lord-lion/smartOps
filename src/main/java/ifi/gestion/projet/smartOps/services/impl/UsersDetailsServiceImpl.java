package ifi.gestion.projet.smartOps.services.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountLockedException;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ifi.gestion.projet.smartOps.entities.Employer;
import ifi.gestion.projet.smartOps.entities.Status;
import ifi.gestion.projet.smartOps.repositories.EmployerRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Slf4j
@Service
public class UsersDetailsServiceImpl implements UserDetailsService{
	private final EmployerRepository employerRepository = null;

	   // @SneakyThrows
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Employer employer = employerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid email"));

	            try {
	                if(employer.getStatus() == Status.INACTIVE)
	                    throw new AccountLockedException("Your account is locked please contact system administrator");
	            } catch (AccountLockedException e) {

	            throw new RuntimeException(e);
	        }
	        return User.withUsername(employer.getEmail())
	                .password(employer.getPassword())
	                .authorities(getAuthorities(employer)).build();
	    }

	    private Set<SimpleGrantedAuthority> getAuthorities(Employer user) {
	        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        user.getRoles().stream().map(role -> {
	            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
	            return role;
	        }).collect(Collectors.toSet());
	        return authorities;
	    }
}
