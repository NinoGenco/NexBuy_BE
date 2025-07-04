package org.polimi.nexbuy.exception.products;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

    public CategoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
