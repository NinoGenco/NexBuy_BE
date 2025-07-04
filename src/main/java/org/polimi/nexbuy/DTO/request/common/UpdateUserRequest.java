package org.polimi.nexbuy.DTO.request.common;

import lombok.Data;

@Data
public class UpdateUserRequest extends RegisterRequest {

    private long id;
}
