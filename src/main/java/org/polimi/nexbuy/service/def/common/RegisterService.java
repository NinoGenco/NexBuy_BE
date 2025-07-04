package org.polimi.nexbuy.service.def.common;

import org.polimi.nexbuy.DTO.request.common.RegisterRequest;
import org.polimi.nexbuy.DTO.response.common.UserResponse;

public interface RegisterService {
    UserResponse registerUser(RegisterRequest request);
}
