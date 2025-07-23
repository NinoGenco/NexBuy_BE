package org.polimi.nexbuy.security.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.polimi.nexbuy.service.implementation.TokenServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Classe per la gestione dei token JWT
 */
@Service
@AllArgsConstructor
public class JwtService {

    /**
     * Service per la gestione dei token
     */
    private final TokenServiceImpl tokenService;

    /**
     * Chiave segreta per la generazione del token
     */
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    /**
     * Metodo per l'estrazione dell'username dal token
     * @param token Token
     * @return Username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Metodo per l'estrazione di un claim dal token
     * @param token Token
     * @param claimsResolver Risolutore dei claims
     * @param <T> Tipo del claim
     * @return Claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Metodo per la generazione del token
     * @param userDetails Dettagli dell'utente
     * @return Token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Metodo per la generazione del token
     * @param extraClaims Claims aggiuntivi
     * @param userDetails Dettagli dell'utente
     * @return Token
     */
    public String generateToken(
            Map<String, Objects> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Metodo per la validazione del token
     * @param token Token
     * @param userDetails Dettagli dell'utente
     * @return Risultato della validazione
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

    /**
     * Metodo per il controllo della scadenza del token
     * @param token Token
     * @return Risultato del controllo
     */
    public boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate != null && new Date().after(expirationDate);
    }

    /**
     * Metodo per l'estrazione della data di scadenza dal token
     * @param token Token
     * @return Data di scadenza
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Metodo per l'estrazione di tutti i claims dal token
     * @param token Token
     * @return Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Metodo per la generazione della chiave di firma
     * @return Chiave di firma
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Metodo per la gestione di un token scaduto
     * @param token Token
     */
    public void handleExpiredJwtException(String token) {

        tokenService.deleteToken(token);

    }

}
