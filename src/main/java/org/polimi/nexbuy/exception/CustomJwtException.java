package org.polimi.nexbuy.exception;

/**
 * Eccezione che viene lanciata quando c'è un errore nella creazione del token
 */
public class CustomJwtException extends RuntimeException {

    public CustomJwtException(String message) {
        super(message);
    }

}
