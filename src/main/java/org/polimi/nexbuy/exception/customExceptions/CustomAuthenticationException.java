package org.polimi.nexbuy.exception.customExceptions;

/**
 * Eccezione che viene lanciata quando l'autenticazione non è corretta
 */
public class CustomAuthenticationException extends RuntimeException {

    public CustomAuthenticationException(String message) {
        super(message);
    }

    public CustomAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

}
