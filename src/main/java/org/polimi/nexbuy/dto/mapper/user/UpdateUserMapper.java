package org.polimi.nexbuy.dto.mapper.user;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.user.UpdateUserDTO;
import org.polimi.nexbuy.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserMapper {

    public User userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof UpdateUserDTO userData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setFirstName(userData.getFirstName());
            user.setLastName(userData.getLastName());
            user.setEmail(userData.getEmail());
            user.setUsername(userData.getUsername());
            return user;
        }
    }

}
