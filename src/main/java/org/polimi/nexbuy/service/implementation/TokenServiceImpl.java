package org.polimi.nexbuy.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.polimi.nexbuy.model.Token;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.repository.TokenRepository;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe Service per la gestione dei token.
 */
@Service
@Transactional
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    /**
     * Metodo per l'aggiunta di un token.
     * @param token Token da aggiungere.
     * @param user Utente a cui associare il token.
     */
    @Override
    public void addToken(String token, User user) throws Exception {

        Optional<User> extractUser = userRepository.findById(user.getId());

        if (extractUser.isPresent()) {
            tokenRepository.save(new Token(token, extractUser.get()));
        } else {
            throw new Exception("Errore durante la creazione del token");
        }

    }

    /**
     * Metodo per la cancellazione di un token.
     * @param token Token da cancellare.
     */
    @Override
    public void deleteToken(String token) {
        tokenRepository.deleteByToken(token);
    }
}
