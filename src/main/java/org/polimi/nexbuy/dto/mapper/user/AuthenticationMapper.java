package org.polimi.nexbuy.dto.mapper.user;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.user.AuthenticationDTO;
import org.polimi.nexbuy.model.User;
import org.springframework.stereotype.Component;

/**
 * Classe che si occupa di mappare un UserAuthenticationDTO in un User
 */
@Component
public class AuthenticationMapper {

    /**
     * Metodo che mappa un UserAuthenticationDTO in un User
     * @param userDTO oggetto UserAuthenticationDTO da convertire
     * @return oggetto User convertito
     */
    public User userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof AuthenticationDTO userData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setUsername(userData.getUsername());
            user.setPassword(userData.getPassword());
            return user;
        }
    }

}
