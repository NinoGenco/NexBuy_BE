package org.polimi.nexbuy.service.impl;


import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.LoginRequest;
import org.polimi.nexbuy.DTO.request.RegisterRequest;
import org.polimi.nexbuy.DTO.response.UserResponse;
import org.polimi.nexbuy.exception.AuthException;
import org.polimi.nexbuy.model.Role;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.def.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail());
        System.out.println(loginRequest.getPassword());
        return userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword()).orElseThrow(() -> new AuthException("Credenziali non valide"));
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        if (request != null){
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setDateOfBirth(request.getDateOfBirth());
            user.setTelephone(request.getTelephone());
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);

            return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getTelephone(), user.getRole());
        } else {
            return null;
        }
    }


}