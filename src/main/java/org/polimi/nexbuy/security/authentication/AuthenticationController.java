package org.polimi.nexbuy.security.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.user.AuthenticationDTO;
import org.polimi.nexbuy.dto.input.user.RegistrationDTO;
import org.polimi.nexbuy.dto.mapper.user.AuthenticationMapper;
import org.polimi.nexbuy.dto.mapper.user.RegistrationMapper;
import org.polimi.nexbuy.exception.SendingMailException;
import org.polimi.nexbuy.exception.customExceptions.BadCredentialsException;
import org.polimi.nexbuy.response.ResponseMessage;
import org.polimi.nexbuy.security.configuration.JwtService;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

/**
 * Classe Controller per la gestione delle richieste di autenticazione
 */
@SuppressWarnings("All")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * Service per la gestione delle richieste di autenticazione
     */
    private final AuthenticationService authenticationService;
    private final AuthenticationService userService;

    /**
     * Mapper per la conversione dei DTO
     */
    private final RegistrationMapper userRegistrationMapper;
    private final AuthenticationMapper userAuthenticationMapper;

    /**
     * Service per la gestione dei token JWT
     */
    private final JwtService jwtService;

    /**
     * Metodo per la registrazione di un utente
     * @param userDTO DTO dell'utente da registrare
     * @return ResponseEntity<ResponseMessage> Risposta della richiesta
     * @throws UserAlreadyExistException Eccezione lanciata se l'utente è già registrato
     * @throws InvalidDataException Eccezione lanciata se i dati inseriti non sono validi
     * @throws SendingMailException Eccezione lanciata se la mail di conferma non può essere inviata
     * @throws UnregisteredUserException Eccezione lanciata se l'utente non è registrato
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseMessage> register(
            @RequestBody InputDTO userDTO
    ) throws SendingMailException {

        try {
            if (userDTO instanceof RegistrationDTO) {
                userService.register(userRegistrationMapper.userDTOToUser(userDTO));

                return new ResponseEntity<>(new ResponseMessage("Utente registrato"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Utente non aggiornato - CustomType non valido");
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Messaggio non leggibile");
        }

    }

    /**
     * Metodo per l'autenticazione di un utente
     * @param userDTO DTO dell'utente da autenticare
     * @return ResponseEntity<AuthenticationResponse> Risposta della richiesta
     * @throws BadCredentialsException Eccezione lanciata se le credenziali sono errate
     * @throws UserNotFoundException Eccezione lanciata se l'utente non è registrato
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody InputDTO userDTO
    ) throws Exception {

        try {
            if (userDTO instanceof AuthenticationDTO) {
                AuthenticationResponse response = userService.authenticate(userAuthenticationMapper.userDTOToUser(userDTO));

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Utente non autenticato");
            }
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Invalid data");
        }
    }

    /**
     * Metodo per il logout di un utente
     * @param request Richiesta HTTP
     * @return ResponseEntity<ResponseMessage> Risposta della richiesta
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> logout(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            authenticationService.logout(token);

            return new ResponseEntity<>(new ResponseMessage("L'utente si è disconnesso"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("L'utente non si è disconnesso"), HttpStatus.UNAUTHORIZED);
        }
    }

}
