package org.polimi.nexbuy.service.def;

import org.polimi.nexbuy.DTO.request.UpdateUserRequest;
import org.polimi.nexbuy.DTO.response.UserResponse;
import org.polimi.nexbuy.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //Implementazione della classe UserService per gestire le funzionalità di un utente
    //Metodo per eliminare, modificare e ricerca di un utente tramite ID

    void disableUser(long id);
    User updateUser(UpdateUserRequest updateUserRequest);
    User findUserById(long id);
    List<UserResponse> findAllUsers();

    User findByEmail(String email);
}
