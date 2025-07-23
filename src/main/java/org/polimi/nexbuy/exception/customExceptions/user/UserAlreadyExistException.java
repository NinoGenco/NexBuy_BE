package org.polimi.nexbuy.exception.customExceptions.user;

/**
 * Eccezione che viene lanciata quando l'utente è già presente
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message){
        super(message);
    }

}
