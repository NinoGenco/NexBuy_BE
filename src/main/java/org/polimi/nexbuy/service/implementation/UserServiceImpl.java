package org.polimi.nexbuy.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.polimi.nexbuy.dto.output.user.UserSummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.exception.DuplicateEmailException;
import org.polimi.nexbuy.exception.customExceptions.InvalidDataException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.UserService;
import org.polimi.nexbuy.utils.ObjectUpdater;
import org.polimi.nexbuy.utils.SecurityUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe Service per la gestione degli utenti.
 */
@SuppressWarnings("All")
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean updateUser(String username, User user) throws IllegalAccessException, DuplicateEmailException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        User userToUpdate = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con username: " + username));

        if (!currentUser.getUsername().equals(userToUpdate.getUsername())) {
            throw new IllegalAccessException("Non sei autorizzato a modificare questi dati!");
        }

        if (!user.getEmail().equalsIgnoreCase(userToUpdate.getEmail())) {
            userRepository.findByEmail(user.getEmail())
                    .ifPresent(existingUser -> {
                        if (!existingUser.getUsername().equals(userToUpdate.getUsername())) {
                            try {
                                throw new DuplicateEmailException("Email già utilizzata da un altro utente!");
                            } catch (DuplicateEmailException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        }

        user.setUsername(userToUpdate.getUsername());

        ObjectUpdater<User> userUpdater = new ObjectUpdater<>();
        boolean toUpdate = userUpdater.updateObject(userToUpdate, user, currentUsername);

        if (toUpdate) {
            userRepository.save(userToUpdate);
            //sendUpdateEmail(userToUpdate);
        }

        return toUpdate;
    }

    @Override
    public boolean updateRole(String username, UserRoles role) throws IllegalAccessException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        User userToUpdate = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con username: " + username));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Solo Admin e Super Admin sono autorizzati a modificare i ruoli!");
        }

        if (currentUser.getRole().equals(UserRoles.ROLE_ADMIN)) {
            if (userToUpdate.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
                throw new IllegalAccessException("Non sei autorizzato a modificare un Super Admin!");
            }
            if (role.equals(UserRoles.ROLE_SUPER_ADMIN)) {
                throw new IllegalAccessException("Non sei autorizzato a impostare il ruolo Super Admin!");
            }
        }

        if (userToUpdate.getRole() == role) {
            return false;
        }

        userToUpdate.setRole(role);
        userRepository.save(userToUpdate);
        return true;
    }

    @Override
    public void updatePassword(String username, String password) throws InvalidDataException, UserNotFoundException, IllegalAccessException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        User userToUpdate = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con username: " + username));

        if (!currentUser.getUsername().equals(userToUpdate.getUsername())) {
            throw new IllegalAccessException("Non sei autorizzato a modificare questi dati!");
        }

        if (passwordEncoder.matches(password, currentUser.getPassword())) {
            throw new InvalidDataException("La nuova password non può essere uguale alla vecchia!");
        }

        userToUpdate.setPassword(passwordEncoder.encode(password));
        userRepository.save(userToUpdate);
    }

    @Override
    public UserSummaryDTO getUserByUsername(String username) throws UserNotFoundException, IllegalAccessException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        User requestedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con username: " + username));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN) &&
                !currentUser.getUsername().equals(requestedUser.getUsername())) {
            throw new IllegalAccessException("Non sei autorizzato a visualizzare questi dati!");
        }

        return new UserSummaryDTO(requestedUser);
    }

    @Override
    public List<UserSummaryDTO> getAllUsers() throws DataAccessServiceException, IllegalAccessException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Solo Admin e Super Admin possono visualizzare tutti gli utenti!");
        }

        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(UserSummaryDTO::new)
                    .toList();
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante il recupero degli utenti!");
        }
    }

}
