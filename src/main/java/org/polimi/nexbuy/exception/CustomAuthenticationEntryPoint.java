package org.polimi.nexbuy.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * Classe che gestisce gli errori di autenticazione e autorizzazione
 */
@SuppressWarnings("All")
@Component
@RestControllerAdvice
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    /**
     * Metodo che gestisce l'errore di autenticazione
     * @param request richiesta
     * @param response risposta
     * @param authException eccezione
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized: Authentication is required and was not provided.\", \"status\": 401}");

    }

    /**
     * Metodo che gestisce l'errore di autorizzazione
     * @param request richiesta
     * @param response risposta
     * @param accessDeniedException eccezione
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Forbidden: Access is denied.\", \"status\": 403}");

    }
}
