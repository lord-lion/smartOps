package ifi.gestion.projet.smartOps.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
	@SuppressWarnings("unused")
	private String token;
}
