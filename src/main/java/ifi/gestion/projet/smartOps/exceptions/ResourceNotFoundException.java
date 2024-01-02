package ifi.gestion.projet.smartOps.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends Throwable{
	public ResourceNotFoundException(String message) {
        super(message);
    }
}
