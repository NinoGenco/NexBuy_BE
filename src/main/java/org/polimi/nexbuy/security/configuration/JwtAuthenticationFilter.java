package org.polimi.nexbuy.security.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.exception.ExceptionManager;
import org.polimi.nexbuy.exception.customExceptions.user.UserExceptionManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro per l'autenticazione JWT
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Service per la gestione dei token JWT
     */
    private final JwtService jwtService;

    /**
     * Service per la gestione degli utenti
     */
    private final UserDetailsService userDetailsService;

    /**
     * Manager per la gestione delle eccezioni
     * esso viene richiamato perchè nel filtro la gestione delle exception è diversa
     */
    private final ExceptionManager exceptionManager;

    /**
     * Manager per la gestione delle eccezioni degli utenti
     */
    private final UserExceptionManager userExceptionManager;

    /**
     * Metodo per l'autenticazione dell'utente
     * @param request Richiesta HTTP
     * @param response Risposta HTTP
     * @param filterChain Filtro
     * @throws ServletException Eccezione lanciata in caso di errore
     * @throws IOException Eccezione lanciata in caso di errore
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        try {
            username = jwtService.extractUsername(jwt);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (ExpiredJwtException e) {
            jwtService.handleExpiredJwtException(jwt);
            exceptionManager.handleCustomExpiredJwtException(e);
        } catch (SignatureException e) {
            exceptionManager.handleCustomSignatureException(e);
        } catch (MalformedJwtException e) {
            exceptionManager.handleMalformedJwtException(e);
        } catch (JwtException e) {
            exceptionManager.handleGenericJwtException(e);
        }  catch (UsernameNotFoundException e) {
            userExceptionManager.handleUsernameNotFoundException(e);
        }
        filterChain.doFilter(request, response);
    }
}
