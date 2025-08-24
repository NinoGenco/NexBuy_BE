package org.polimi.nexbuy.dto.input.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.model.enums.UserRoles;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRoleDTO implements InputDTO {

    private UserRoles role;

}
