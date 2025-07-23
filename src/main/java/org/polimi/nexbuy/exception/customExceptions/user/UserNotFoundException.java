package org.polimi.nexbuy.exception.customExceptions.user;

/**
 * Eccezione che viene lanciata quando l'utente non è stato trovato
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }

}
