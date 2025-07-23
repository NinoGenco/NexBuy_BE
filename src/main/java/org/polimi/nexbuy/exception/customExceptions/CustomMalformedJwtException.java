package org.polimi.nexbuy.exception.customExceptions;

/**
 * Eccezione che viene lanciata quando il JWT non è ben formato
 */
public class CustomMalformedJwtException extends RuntimeException {

    public CustomMalformedJwtException(String message) {
        super(message);
    }

    public CustomMalformedJwtException(String message, Throwable cause) {
        super(message, cause);
    }

}
