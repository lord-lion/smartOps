package ifi.gestion.projet.smartOps.dto.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
	@SuppressWarnings("unused")
	private HttpStatus status;

    @SuppressWarnings("unused")
	private String error_code;

    @SuppressWarnings("unused")
	private String message;

    @SuppressWarnings("unused")
	private LocalDateTime timeStamp;
}
