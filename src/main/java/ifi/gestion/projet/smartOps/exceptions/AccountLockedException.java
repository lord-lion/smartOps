package ifi.gestion.projet.smartOps.exceptions;

@SuppressWarnings("serial")
public class AccountLockedException extends Throwable{
	public AccountLockedException(String message) {
        super(message);
    }
}
