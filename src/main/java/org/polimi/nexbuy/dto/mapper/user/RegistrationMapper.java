package org.polimi.nexbuy.dto.mapper.user;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.user.RegistrationDTO;
import org.polimi.nexbuy.model.User;
import org.springframework.stereotype.Component;

/**
 * Classe che si occupa di mappare un UserRegistrationDTO in un User
 */
@Component
public class RegistrationMapper {

    /**
     * Metodo che mappa un UserRegistrationDTO in un User
     * @param userDTO oggetto UserRegistrationDTO da convertire
     * @return oggetto User convertito
     */
    public User userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof RegistrationDTO userData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setFirstName(userData.getFirstName());
            user.setLastName(userData.getLastName());
            user.setEmail(userData.getEmail());
            user.setUsername(userData.getUsername());
            user.setPassword(userData.getPassword());
            return user;
        }
    }

}
