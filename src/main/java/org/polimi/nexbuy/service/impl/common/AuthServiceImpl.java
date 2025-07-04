package org.polimi.nexbuy.service.impl.common;


import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.common.LoginRequest;
import org.polimi.nexbuy.DTO.request.common.RegisterRequest;
import org.polimi.nexbuy.DTO.response.common.UserResponse;
import org.polimi.nexbuy.exception.common.AuthException;
import org.polimi.nexbuy.model.enums.Role;
import org.polimi.nexbuy.model.common.User;
import org.polimi.nexbuy.repository.common.UserRepository;
import org.polimi.nexbuy.service.def.common.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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