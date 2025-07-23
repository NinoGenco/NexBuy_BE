package org.polimi.nexbuy.exception.customExceptions;

/**
 * Eccezione che viene lanciata quando l'utente non ha i permessi per accedere a una risorsa
 */
public class CustomAccessDeniedException extends RuntimeException {

    public CustomAccessDeniedException(String message) {
        super(message);
    }

    public CustomAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

}
