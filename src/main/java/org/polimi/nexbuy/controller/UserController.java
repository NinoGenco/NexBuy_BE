package org.polimi.nexbuy.controller;

import lombok.AllArgsConstructor;
import org.polimi.nexbuy.dto.output.user.UserSummaryDTO;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.service.implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("All")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserSummaryDTO> getUserByUsername (@PathVariable String username) throws UserNotFoundException {

        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);

    }

}
