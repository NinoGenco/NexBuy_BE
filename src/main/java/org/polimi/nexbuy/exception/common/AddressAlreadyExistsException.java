package org.polimi.nexbuy.exception.common;

public class AddressAlreadyExistsException extends RuntimeException {

    public AddressAlreadyExistsException(String message) {
        super(message);
    }

    public AddressAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
