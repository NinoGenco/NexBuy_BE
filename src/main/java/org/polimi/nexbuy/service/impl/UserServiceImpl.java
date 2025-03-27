package org.polimi.nexbuy.service.impl;


import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.UpdateUserRequest;
import org.polimi.nexbuy.DTO.response.UserResponse;
import org.polimi.nexbuy.exception.UserNotFoundException;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.def.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User updateUser(UpdateUserRequest updateUserRequest) {
        Optional<User> u = userRepository.findByEmail(updateUserRequest.getEmail());
        //Prendo i dati dell'option e li trasferisco nell'utente
        User user = u.get();
        user.setEmail(updateUserRequest.getEmail());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        user.setTelephone(updateUserRequest.getTelephone());
        user.setDateOfBirth(updateUserRequest.getDateOfBirth());
        return userRepository.save(user);

    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente con ID" + id + " non trovato"));
    }

    @Override
    public void disableUser(long id) {
        User u = userRepository.findById(id).orElse(null);
        if (u == null) {
            throw new UserNotFoundException("Utente non trovato");
        } else {
            u.setEnabled(false);
            userRepository.save(u);
            System.out.println("Utente disabilitato");
        }
    }

    public List<UserResponse> findAllUsers() {
        return userRepository.findAllUsersOrderByLastNameAsc();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}