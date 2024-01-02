package ifi.gestion.projet.smartOps.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ifi.gestion.projet.smartOps.repositories.EmployerRepository;
import ifi.gestion.projet.smartOps.repositories.*;
import ifi.gestion.projet.smartOps.services.impl.*;

@Configuration
public class BeanConfiguration {
	@Bean
    public EmployerServiceImpl empServiceImpl(EmployerRepository employerRepository, PasswordEncoder bcryptEncoder, RoleRepository roleRepository) {
        return new EmployerServiceImpl(employerRepository, bcryptEncoder, roleRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
