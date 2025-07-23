package org.polimi.nexbuy.exception.customExceptions;

/**
 * Eccezione che viene lanciata quando la firma del JWT non è valida
 */
public class CustomInvalidJwtSignatureException extends RuntimeException {

    public CustomInvalidJwtSignatureException(String message) {
        super(message);
    }

    public CustomInvalidJwtSignatureException(String message, Throwable cause) {
        super(message, cause);
    }

}
