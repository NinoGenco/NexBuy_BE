package org.polimi.nexbuy.exception;

//utilizzata per gestire quando un prodotto non viene trovato
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
