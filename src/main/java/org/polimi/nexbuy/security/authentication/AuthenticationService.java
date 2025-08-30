package org.polimi.nexbuy.security.authentication;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.LockAcquisitionException;
import org.polimi.nexbuy.exception.SendingMailException;
import org.polimi.nexbuy.exception.customExceptions.BadCredentialsException;
import org.polimi.nexbuy.exception.customExceptions.InvalidDataException;
import org.polimi.nexbuy.exception.customExceptions.user.UnregisteredUserException;
import org.polimi.nexbuy.exception.customExceptions.user.UserAlreadyExistException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;
import org.polimi.nexbuy.repository.TokenRepository;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.security.configuration.JwtService;
import org.polimi.nexbuy.service.implementation.TokenServiceImpl;
import org.polimi.nexbuy.service.mail.EmailService;
import org.polimi.nexbuy.utils.validation.validator.UserRegistrationValidator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe Service per la gestione delle richieste di autenticazione
 */
@SuppressWarnings("All")
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    /**
     * Repository per la gestione degli utenti
     */
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final TokenServiceImpl tokenService;

    /**
     * Encoder per la codifica delle password
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Service per la gestione dei token JWT
     */
    private final JwtService jwtService;

    /**
     * Manager per l'autenticazione
     */
    public final AuthenticationManager authenticationManager;

    /**
     * Service per l'invio delle mail
     */
    private final EmailService emailService;

    /**
     * Validator per la validazione dei dati
     */
    private final UserRegistrationValidator validator;



    /**
     * Metodo per la registrazione di un utente
     * @param user Utente da registrare
     * @throws UserAlreadyExistException Eccezione lanciata se l'utente è già registrato
     * @throws InvalidDataException Eccezione lanciata se i dati inseriti non sono validi
     * @throws SendingMailException Eccezione lanciata se la mail di conferma non può essere inviata
     * @throws UnregisteredUserException Eccezione lanciata se l'utente non è registrato
     */
    public void register(User user) throws UserAlreadyExistException, InvalidDataException, SendingMailException, UnregisteredUserException {

        if (!(userRepository.existsByEmail(user.getEmail()))) {

            validator.validateRegistration(user);

            var userToInsert = User.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role(UserRoles.ROLE_USER)
                    .build();

            try {

                userRepository.save(userToInsert);

            } catch (ConstraintViolationException | DataIntegrityViolationException | TransientObjectException | LockAcquisitionException e){
                throw new UnregisteredUserException("Errore durante la registrazione: qualcosa è andato storto!");
            }

            sendRegistrationEmail(userToInsert);

        } else {
            throw new UserAlreadyExistException("Errore durante la registrazione!");
        }
    }

    /**
     * Metodo per l'autenticazione di un utente
     * @param user Utente da autenticare
     * @return AuthenticationResponse Risposta dell'autenticazione
     * @throws BadCredentialsException Eccezione lanciata se le credenziali sono errate
     * @throws UserNotFoundException Eccezione lanciata se l'utente non è registrato
     * @throws ExpiredJwtException Eccezione lanciata se il token è scaduto
     */
    public AuthenticationResponse authenticate(User user) throws Exception {

        var userToAuthenticate = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Errore durante l'autenticazione: credenziali errate!"));

        if (!passwordEncoder.matches(user.getPassword(), userToAuthenticate.getPassword())) {
            throw new BadCredentialsException("Errore durante l'autenticazione: credenziali errate!");
        }

        String token = tokenRepository.findTokenByUser(userToAuthenticate);

        if (token != null) {
            jwtService.handleExpiredJwtException(token);
        }

        token = jwtService.generateToken(userToAuthenticate);
        tokenService.addToken(token, userToAuthenticate);

        return AuthenticationResponse.builder().token(token).build();
    }


    /**
     * Metodo per il logout di un utente
     * @param token Token da eliminare
     */
    public void logout(String token) {
        tokenService.deleteToken(token);
    }

    /**
     * Metodo per il caricamento di un utente tramite username
     * @param username Username dell'utente
     * @return UserDetails Utente trovato
     * @throws UsernameNotFoundException Eccezione lanciata se l'utente non è registrato
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con username: " + username));
    }

    /**
     * Metodo per l'invio della mail di registrazione
     * @param user Utente a cui inviare la mail
     * @throws SendingMailException Eccezione lanciata se la mail di conferma non può essere inviata
     */
    private void sendRegistrationEmail(User user) throws SendingMailException {

        Map<String, Object> emailModel = new HashMap<>();
        emailModel.put("subject", "Registrazione utente");
        emailModel.put("name", user.getFirstName());

        emailService.sendRegistrationEmail(user.getEmail(), (String) emailModel.get("subject"), null, null, "user-registration-email", emailModel);

    }

}
