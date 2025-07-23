package org.polimi.nexbuy.utils;

import org.polimi.nexbuy.exception.customExceptions.CustomAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Classe di utilità per la gestione della sicurezza
 */
@Component
public class SecurityUtils {

    /**
     * Restituisce l'username dell'utente corrente
     * @return username dell'utente corrente
     */
    public static String getCurrentUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomAuthenticationException("La registrazione richiede l'autenticazione!");
        }

        return authentication.getName();
    }

}
