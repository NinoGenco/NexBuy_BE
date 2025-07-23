package org.polimi.nexbuy.exception.customExceptions.user;

/**
 * Eccezione che viene lanciata quando qualcosa va storto con l'utente
 */
public class UnregisteredUserException extends RuntimeException {

    public UnregisteredUserException(String message) {
        super(message);
    }

}
