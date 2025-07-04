package org.polimi.nexbuy.service.def.common;

import org.polimi.nexbuy.DTO.request.common.UpdateUserRequest;
import org.polimi.nexbuy.DTO.response.common.UserResponse;
import org.polimi.nexbuy.model.common.User;

import java.util.List;

public interface UserService {

    //Implementazione della classe UserService per gestire le funzionalità di un utente
    //Metodo per eliminare, modificare e ricerca di un utente tramite ID

    void disableUser(long id);
    User updateUser(UpdateUserRequest updateUserRequest);
    User findUserById(long id);
    List<UserResponse> findAllUsers();

    User findByEmail(String email);
}
