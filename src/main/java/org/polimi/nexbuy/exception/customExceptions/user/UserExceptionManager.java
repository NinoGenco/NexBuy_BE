package org.polimi.nexbuy.exception.customExceptions.user;

import org.polimi.nexbuy.exception.ErrorMessage;
import org.polimi.nexbuy.utils.RequestMappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;

/**
 * Classe che gestisce le eccezioni lanciate per gli User
 */
@SuppressWarnings("All")
@RestControllerAdvice
public class UserExceptionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionManager.class);

    @ExceptionHandler(value = {UnregisteredUserException.class})
    public ResponseEntity<ErrorMessage> handleUnregisteredUserException(UnregisteredUserException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("UnregisteredUserException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> handleUserAlreadyExistsException(UserAlreadyExistException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.CONFLICT.value());
        errorMessage.setError("CONFLICT");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("UserAlreadyExistsException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorMessage.setError("NOT FOUND");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("UserNotFoundException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleUsernameNotFoundException(UsernameNotFoundException exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorMessage.setError("NOT FOUND");
        errorMessage.setMessage("Utenza non trovata");

        LOGGER.error("UsernameNotFoundException: {}", errorMessage.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
