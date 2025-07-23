package org.polimi.nexbuy.exception.customExceptions;

/**
 * Eccezione che viene lanciata quando l'utente non è autenticato
 */
public class CustomAnonymousAuthenticationToken extends RuntimeException {

    public CustomAnonymousAuthenticationToken(String message) {
        super(message);
    }

}
