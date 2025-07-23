package org.polimi.nexbuy.exception.customExceptions;

/**
 * Eccezione che viene lanciata quando i dati non sono validi
 */
public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message){
        super(message);
    }

}
