package org.polimi.nexbuy.exception;

import lombok.Getter;

/**
 * Eccezione che viene lanciata quando il token è scaduto
 */
@Getter
public class CustomExpiredJwtException extends RuntimeException {

    private String token;

    public CustomExpiredJwtException(String message) {
        super(message);
    }

    public CustomExpiredJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomExpiredJwtException(String message, String token) {
        super(message);
        this.token = token;
    }

}
