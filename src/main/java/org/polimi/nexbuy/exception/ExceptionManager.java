package org.polimi.nexbuy.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.polimi.nexbuy.exception.customExceptions.*;
import org.polimi.nexbuy.utils.RequestMappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Classe globale che gestisce tutte le eccezioni comuni all'interno di tutto il progetto
 */
@SuppressWarnings("All")
@RestControllerAdvice
public class ExceptionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionManager.class);

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("BadCredentialsException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(value = {IllegalAccessException.class})
    public ResponseEntity<ErrorMessage> handleIllegalAccessException(IllegalAccessException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.FORBIDDEN.value());
        errorMessage.setError("FORBIDDEN");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("IllegalAccessException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ErrorMessage> handleCustomExpiredJwtException(ExpiredJwtException exception) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());

        LOGGER.error("CustomExpiredJwtException: {}", errorMessage.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {SignatureException.class})
    public ResponseEntity<ErrorMessage> handleCustomSignatureException(SignatureException exception) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());

        LOGGER.error("CustomInvalidJwtSignatureException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    public ResponseEntity<ErrorMessage> handleMalformedJwtException(MalformedJwtException exception) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());

        LOGGER.error("CustomMalformedJwtException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {JwtException.class})
    public ResponseEntity<ErrorMessage> handleGenericJwtException(JwtException exception) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());

        LOGGER.error("CustomJwtException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {CustomAuthenticationException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(CustomAuthenticationException exception, HandlerMethod handlerMethod) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("CustomAuthenticationException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {CustomAccessDeniedException.class})
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(CustomAccessDeniedException exception, HandlerMethod handlerMethod) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.FORBIDDEN.value());
        errorMessage.setError("FORBIDDEN");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("CustomAccessDeniedException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {CustomAnonymousAuthenticationToken.class})
    public ResponseEntity<ErrorMessage> handleAnonymousAuthenticationToken(CustomAnonymousAuthenticationToken exception, HandlerMethod handlerMethod) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("CustomAnonymousAuthenticationToken: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {DataAccessServiceException.class})
    public ResponseEntity<ErrorMessage> handleDataAccessServiceException(DataAccessServiceException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMessage.setError("INTERNAL SERVER ERROR");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("DataAccessServiceException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("DataIntegrityViolationException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ClassCastException.class})
    public ResponseEntity<ErrorMessage> handleClassCastException(ClassCastException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("ClassCastException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {SendingMailException.class})
    public ResponseEntity<ErrorMessage> handleMailException(SendingMailException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMessage.setError("INTERNAL SERVER ERROR");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("MailException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = {DateTimeParseException.class})
    public ResponseEntity<ErrorMessage> handleDateTimeParseException(DateTimeParseException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("DateTimeParseException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage("Message Not Readable");
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("HttpMessageNotReadableException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("IllegalArgumentException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {InvalidDataException.class})
    public ResponseEntity<ErrorMessage> handleInvalidDataException(InvalidDataException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("InvalidDataException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

}
