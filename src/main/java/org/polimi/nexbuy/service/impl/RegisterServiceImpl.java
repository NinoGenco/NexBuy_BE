package org.polimi.nexbuy.service.impl;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.RegisterRequest;
import org.polimi.nexbuy.DTO.response.UserResponse;
import org.polimi.nexbuy.model.Role;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.def.RegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse registerUser(RegisterRequest request) {
        // Controlla se l'email è già registrata
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("L'email è già in uso.");
        }

        // Crea un nuovo utente
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setDateOfBirth(request.getDateOfBirth());
        newUser.setTelephone(request.getTelephone());
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // Crittografia della password
        newUser.setRole(Role.ROLE_USER); // Assegna il ruolo predefinito

        // Salva l'utente nel database
        User savedUser = userRepository.save(newUser);

        // Restituisce una risposta con i dettagli dell'utente registrato
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getDateOfBirth(),
                savedUser.getTelephone(),
                savedUser.getRole());
    }
}
