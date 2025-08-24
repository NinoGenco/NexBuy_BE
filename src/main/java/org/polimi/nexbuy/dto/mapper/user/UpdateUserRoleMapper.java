package org.polimi.nexbuy.dto.mapper.user;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.user.UpdateUserDTO;
import org.polimi.nexbuy.dto.input.user.UpdateUserRoleDTO;
import org.polimi.nexbuy.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserRoleMapper {

    public User userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof UpdateUserRoleDTO userData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setUserRoles(userData.getUserRoles());
            return user;
        }
    }

}
