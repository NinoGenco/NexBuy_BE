package org.polimi.nexbuy.dto.mapper.user;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.user.UpdateUserPasswordDTO;
import org.polimi.nexbuy.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserPasswordMapper {

    public User userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof UpdateUserPasswordDTO userData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setPassword(userData.getPassword());
            return user;
        }
    }

}
