package org.polimi.nexbuy.dto.mapper.user;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.user.UpdateUserRoleDTO;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserRoleMapper {

    public UserRoles userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof UpdateUserRoleDTO userData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setUserRoles(userData.getUserRoles());
            return user.getUserRoles();
        }
    }

}
