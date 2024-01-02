package ifi.gestion.projet.smartOps.exceptions;

@SuppressWarnings("serial")
public class PasswordsNotMatchException extends Throwable{
	public PasswordsNotMatchException(String message) {
        super(message);
    }
}
