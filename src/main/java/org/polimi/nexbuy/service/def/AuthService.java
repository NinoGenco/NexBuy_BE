package org.polimi.nexbuy.service.def;

import org.polimi.nexbuy.DTO.request.LoginRequest;
import org.polimi.nexbuy.DTO.request.RegisterRequest;
import org.polimi.nexbuy.DTO.response.UserResponse;
import org.polimi.nexbuy.model.User;

public interface AuthService {

    User login(LoginRequest request);
    UserResponse register(RegisterRequest request);
}
