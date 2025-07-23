package org.polimi.nexbuy.exception;

/**
 * Eccezione che viene lanciata quando si verifica un errore di accesso ai dati
 */
public class DataAccessServiceException extends Exception {

    public DataAccessServiceException(String message) {
        super(message);
    }

}
