package ifi.gestion.projet.smartOps.dto.responses.validations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorMessage {
	@SuppressWarnings("unused")
	private String fieldName;

	  @SuppressWarnings("unused")
	private String message;
}
