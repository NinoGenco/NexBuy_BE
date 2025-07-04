package org.polimi.nexbuy.service.def.common;

import org.polimi.nexbuy.DTO.request.common.LoginRequest;
import org.polimi.nexbuy.DTO.request.common.RegisterRequest;
import org.polimi.nexbuy.DTO.response.common.UserResponse;
import org.polimi.nexbuy.model.common.User;

public interface AuthService {

    User login(LoginRequest request);
    UserResponse register(RegisterRequest request);
}
