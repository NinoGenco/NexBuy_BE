package org.polimi.nexbuy.exception;

//utilizzata per gestire quando un utente non viene trovato
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
