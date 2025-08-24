package org.polimi.nexbuy.dto.input.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO implements InputDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

}
