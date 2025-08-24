package org.polimi.nexbuy.controller;

import lombok.AllArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.user.UpdateUserDTO;
import org.polimi.nexbuy.dto.input.user.UpdateUserPasswordDTO;
import org.polimi.nexbuy.dto.input.user.UpdateUserRoleDTO;
import org.polimi.nexbuy.dto.mapper.user.UpdateUserMapper;
import org.polimi.nexbuy.dto.mapper.user.UpdateUserPasswordMapper;
import org.polimi.nexbuy.dto.mapper.user.UpdateUserRoleMapper;
import org.polimi.nexbuy.dto.output.user.UserSummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.exception.DuplicateEmailException;
import org.polimi.nexbuy.exception.SendingMailException;
import org.polimi.nexbuy.exception.customExceptions.InvalidDataException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.response.ResponseMessage;
import org.polimi.nexbuy.service.implementation.UserServiceImpl;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImpl userService;
    private final UpdateUserMapper updateUserMapper;
    private final UpdateUserPasswordMapper updateUserPasswordMapper;
    private final UpdateUserRoleMapper updateUserRoleMapper;

    @RequestMapping(value = "/update/{username}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable String username,
            @RequestBody InputDTO requestDTO
    ) throws UserNotFoundException, IllegalAccessException, InvalidDataException, DuplicateEmailException, SendingMailException {

        try {
            if (requestDTO instanceof UpdateUserDTO) {
                boolean isUpdated = userService.updateUser(username, updateUserMapper.userDTOToUser(requestDTO));
                if (isUpdated) {
                    return new ResponseEntity<>(new ResponseMessage("Utente aggiornato"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("Nessun campo è stato aggiornato"), HttpStatus.OK);
                }
            } else {
                throw new IllegalArgumentException("Utente non aggiornato - CustomType non valido");
            }
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Messaggio non leggibile");
        }

    }

    @RequestMapping(value = "/update-role/{username}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updateRole(
            @PathVariable String username,
            @RequestBody InputDTO requestDTO
    ) throws UserNotFoundException, IllegalAccessException, DuplicateEmailException {

        try {
            if (requestDTO instanceof UpdateUserRoleDTO) {
                boolean isUpdated = userService.updateRole(username, updateUserRoleMapper.userDTOToUser(requestDTO));
                if (isUpdated) {
                    return new ResponseEntity<>(new ResponseMessage("Utente aggiornato"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("Nessun campo è stato aggiornato"), HttpStatus.OK);
                }
            } else {
                throw new IllegalArgumentException("Utente non aggiornato - CustomType non valido");
            }
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Messaggio non leggibile");
        }
    }

    @RequestMapping(value = "/update-password/{username}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updatePassword(
            @PathVariable String username,
            @RequestBody InputDTO requestDTO
    ) throws InvalidDataException, UserNotFoundException, IllegalAccessException {

        if (requestDTO instanceof UpdateUserPasswordDTO) {
            userService.updatePassword(username, updateUserPasswordMapper.userDTOToUser(requestDTO));
            return new ResponseEntity<>(new ResponseMessage("Password updated"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Password not updated");
        }

    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserSummaryDTO> getUserByUsername (@PathVariable String username) throws UserNotFoundException, IllegalAccessException {

        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);

    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserSummaryDTO>> getAllUsers() throws DataAccessServiceException, IllegalAccessException {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }


}
