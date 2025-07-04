package org.polimi.nexbuy.controller;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.common.LoginRequest;
import org.polimi.nexbuy.DTO.request.common.RegisterRequest;
import org.polimi.nexbuy.DTO.response.common.LoginResponse;
import org.polimi.nexbuy.DTO.response.common.UserResponse;
import org.polimi.nexbuy.security.jwt.JwtUtils;
import org.polimi.nexbuy.service.def.common.AuthService;
import org.polimi.nexbuy.service.def.common.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Ecommerce/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils; //gestione dei token JWT
    private final AuthenticationManager authenticationManager; //autenticazione utenti

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication;
        try {
            //autenticazione dell'utente
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException exception) {
            System.out.println(exception);
            //se le credenziali sono errate, restituisce errore 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Errore: Credenziali non valide", ""));
        }
        //salva l'utente autenticato nel contesto di sicurezza
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //estrai i dettagli dell'utente autenticato
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println(userDetails.getUsername()); //Arrivo qua

        System.out.println(userService.findByEmail(userDetails.getUsername()));

        System.out.println("Arrivo??");

        //genera il token JWT
        String jwtToken = jwtUtils.generateTokenFromUsername(userService.findByEmail(userDetails.getUsername()));

        //restituisce il token JWT e il ruolo dell'utente
        return ResponseEntity.ok(new LoginResponse(jwtToken, userDetails.getAuthorities().iterator().next().getAuthority()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        try {
            //registra il nuovo utente
            UserResponse userResponse = authService.register(request);

            //restituisce i dati dell'utente appena registrato
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
