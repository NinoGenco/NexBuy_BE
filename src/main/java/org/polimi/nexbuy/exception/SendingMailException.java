package org.polimi.nexbuy.exception;

/**
 * Eccezione che viene lanciata quando c'è un errore nell'invio della mail
 */
public class SendingMailException extends Exception{

    public SendingMailException(String message){
        super(message);
    }

}
