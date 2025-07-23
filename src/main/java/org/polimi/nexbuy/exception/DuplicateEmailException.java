package org.polimi.nexbuy.exception;

/**
 * Eccezione che viene lanciata quando l'email è già presente
 */
public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }

}
