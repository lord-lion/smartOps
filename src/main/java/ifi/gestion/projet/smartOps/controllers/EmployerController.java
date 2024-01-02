package ifi.gestion.projet.smartOps.controllers;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ifi.gestion.projet.smartOps.dto.requests.RegisterEmployerRequestDTO;
import ifi.gestion.projet.smartOps.dto.response.EmployerResponseDTO;
import ifi.gestion.projet.smartOps.dto.response.ResponseDTO;
import ifi.gestion.projet.smartOps.dto.requests.*;
import ifi.gestion.projet.smartOps.entities.Employer;
import ifi.gestion.projet.smartOps.exceptions.AlreadyExistsException;
import ifi.gestion.projet.smartOps.exceptions.PasswordsNotMatchException;
import ifi.gestion.projet.smartOps.service.EmployerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/content/management/service/employers/")
@RequiredArgsConstructor
@Slf4j
public class EmployerController {
	private final EmployerService service;
    private final ModelMapper mapper;

    @PostMapping("register")
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ResponseDTO<Object>> register(@Valid @RequestBody RegisterEmployerRequestDTO requestDTO) throws AlreadyExistsException, PasswordsNotMatchException {
        try {
            service.register(mapper.map(requestDTO, Employer.class));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.ok(ResponseDTO.createErrorResponse(HttpStatus.EXPECTATION_FAILED, "Failed to register new member"));
        }
        return ResponseEntity.ok(ResponseDTO.createSuccessResponse(HttpStatus.CREATED, "Member registered successfully"));
    }

    @GetMapping("view-profile")
    public ResponseEntity<ResponseDTO<EmployerResponseDTO>> viewProfile() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        EmployerResponseDTO dto = mapper.map(service.findByEmail(email), EmployerResponseDTO.class);
        ResponseDTO<EmployerResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("edit")
    public ResponseEntity<ResponseDTO<UpdateEmployerRequestDTO>> editProfile(@Valid @RequestBody UpdateEmployerRequestDTO content) {
        try {
            service.edit(mapper.map(content, Employer.class));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDTO.createErrorResponse(HttpStatus.EXPECTATION_FAILED, "Failed to update member"));
        }
        return ResponseEntity.ok(ResponseDTO.createSuccessResponse(HttpStatus.CREATED, "Member edited successfully"));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<EmployerResponseDTO>>> getmembers() {
        List<EmployerResponseDTO> employerResponseDTOS = service.getMembers().stream().map(c -> mapper.map(c, EmployerResponseDTO.class)).toList();

        ResponseDTO<List<EmployerResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(employerResponseDTOS);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("change-status/{id}")
    public ResponseEntity<ResponseDTO<EmployerResponseDTO>> changeStatus(@PathVariable long id) {
        EmployerResponseDTO dto = mapper.map(service.changeStatus(id), EmployerResponseDTO.class);
        ResponseDTO<EmployerResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(dto);
        return ResponseEntity.ok(responseDTO);
    }
}
