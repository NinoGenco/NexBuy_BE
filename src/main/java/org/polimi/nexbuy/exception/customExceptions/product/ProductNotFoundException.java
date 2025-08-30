package org.polimi.nexbuy.exception.customExceptions.product;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

}
