package ifi.gestion.projet.smartOps.dto.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployerResponseDTO {
	@SuppressWarnings("unused")
	private Long id;

    @SuppressWarnings("unused")
	private String firstname;

    @SuppressWarnings("unused")
	private String lastname;

    @SuppressWarnings("unused")
	private String username;

    @SuppressWarnings("unused")
	private String phone;

    @SuppressWarnings("unused")
	private String email;

    @SuppressWarnings("unused")
	private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "UTC")
    private Instant createdDate;

    @SuppressWarnings("unused")
	private ifi.gestion.projet.smartOps.entities.Status status;
}
