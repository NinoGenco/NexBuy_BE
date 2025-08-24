package org.polimi.nexbuy.service;

import org.polimi.nexbuy.dto.output.user.UserSummaryDTO;
import org.polimi.nexbuy.exception.DuplicateEmailException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;

public interface UserService {

    boolean updateUser(User user) throws IllegalAccessException, DuplicateEmailException;

    boolean updateRole(Long id, UserRoles role) throws IllegalAccessException, DuplicateEmailException;

    boolean updatePassword(Long id, String currentPassword, String newPassword) throws IllegalAccessException;

    UserSummaryDTO getUserByUsername(String username) throws UserNotFoundException;

}
