package org.polimi.nexbuy.exception.customExceptions.product;

import org.polimi.nexbuy.exception.ErrorMessage;
import org.polimi.nexbuy.exception.customExceptions.user.UnregisteredUserException;
import org.polimi.nexbuy.exception.customExceptions.user.UserExceptionManager;
import org.polimi.nexbuy.utils.RequestMappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;

@SuppressWarnings("All")
@RestControllerAdvice
public class ProductExceptionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductExceptionManager.class);

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductNotFoundException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorMessage.setError("NOT FOUND");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("ProductNotFoundException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
