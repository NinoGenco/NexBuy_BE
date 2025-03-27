package org.polimi.nexbuy.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.UpdateUserRequest;
import org.polimi.nexbuy.DTO.response.UserResponse;
import org.polimi.nexbuy.service.def.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ecommerce/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //metodo per aggiornare i dati dell'utente
    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UpdateUserRequest request) {
        System.out.println(request.getDateOfBirth());
        try{
            userService.updateUser(request);
            return ResponseEntity.status(HttpStatus.OK).header("Utente aggiornato", "token").build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //metodo per disabilitare un utente
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<String> disable (@PathVariable long id) {
        try {
            userService.disableUser(id);
            return ResponseEntity.status(HttpStatus.OK).header("Utente disabilitato", "token").build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //metodo per ottenere tutti gli utenti
    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponse>> findAll() {
        try {
            List<UserResponse> users = userService.findAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
