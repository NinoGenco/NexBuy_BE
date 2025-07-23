package org.polimi.nexbuy.dto.input.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO implements InputDTO {

    private String username;

    private String password;

}
