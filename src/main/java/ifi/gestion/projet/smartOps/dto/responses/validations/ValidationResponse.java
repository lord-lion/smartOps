package ifi.gestion.projet.smartOps.dto.responses.validations;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResponse {
	@SuppressWarnings("unused")
	private String status;
	  @SuppressWarnings({ "unused", "rawtypes" })
	private List errorMessageList;
}
