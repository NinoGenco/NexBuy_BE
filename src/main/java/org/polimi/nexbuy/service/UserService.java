package org.polimi.nexbuy.service;

import org.polimi.nexbuy.dto.output.user.UserSummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.exception.DuplicateEmailException;
import org.polimi.nexbuy.exception.customExceptions.InvalidDataException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;

import java.util.List;

public interface UserService {

    boolean updateUser(String username, User user) throws IllegalAccessException, DuplicateEmailException;

    boolean updateRole(String username, UserRoles role) throws IllegalAccessException, DuplicateEmailException;

    void updatePassword(String username, String password) throws InvalidDataException, UserNotFoundException, IllegalAccessException;

    UserSummaryDTO getUserByUsername(String username) throws UserNotFoundException, IllegalAccessException;

    List<UserSummaryDTO> getAllUsers() throws DataAccessServiceException, IllegalAccessException;

}
