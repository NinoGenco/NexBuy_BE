package org.polimi.nexbuy.exception;

//utilizzata per gestire errori relativi all'autenticazione
public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
