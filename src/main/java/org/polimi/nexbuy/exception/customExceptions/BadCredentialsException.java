package org.polimi.nexbuy.exception.customExceptions;

/**
 * Eccezione che viene lanciata quando le credenziali non sono corrette
 */
public class BadCredentialsException extends Exception{

    public BadCredentialsException(String message){
        super(message);
    }

}
