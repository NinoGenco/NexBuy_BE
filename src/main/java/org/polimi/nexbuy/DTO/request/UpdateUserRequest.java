package org.polimi.nexbuy.DTO.request;

import lombok.Data;
import org.polimi.nexbuy.model.Role;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
public class UpdateUserRequest extends RegisterRequest {

    private long id;
}
