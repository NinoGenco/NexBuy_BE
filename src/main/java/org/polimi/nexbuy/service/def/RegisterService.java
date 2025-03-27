package org.polimi.nexbuy.service.def;

import org.polimi.nexbuy.DTO.request.RegisterRequest;
import org.polimi.nexbuy.DTO.response.UserResponse;

public interface RegisterService {
    UserResponse registerUser(RegisterRequest request);
}
