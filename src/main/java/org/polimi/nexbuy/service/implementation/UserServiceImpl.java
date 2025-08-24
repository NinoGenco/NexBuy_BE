package org.polimi.nexbuy.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.polimi.nexbuy.dto.output.user.UserSummaryDTO;
import org.polimi.nexbuy.exception.DuplicateEmailException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.UserService;
import org.polimi.nexbuy.utils.ObjectUpdater;
import org.polimi.nexbuy.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public boolean updateUser(User user) throws IllegalAccessException, DuplicateEmailException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        if (!currentUser.getId().equals(user.getId())) {
            throw new IllegalAccessException("Non sei autorizzato a modificare questi dati!");
        }

        if (!user.getEmail().equalsIgnoreCase(currentUser.getEmail())) {
            userRepository.findByEmail(user.getEmail())
                    .ifPresent(existingUser -> {
                        if (!existingUser.getId().equals(user.getId())) {
                            try {
                                throw new DuplicateEmailException("Email già utilizzata da un altro utente!");
                            } catch (DuplicateEmailException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        }

        if (!user.getUsername().equalsIgnoreCase(currentUser.getUsername())) {
            userRepository.findByUsername(user.getUsername())
                    .ifPresent(existingUser -> {
                        if (!existingUser.getId().equals(user.getId())) {
                            try {
                                throw new DuplicateEmailException("Username già utilizzato da un altro utente!");
                            } catch (DuplicateEmailException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        }

        ObjectUpdater<User> userUpdater = new ObjectUpdater<>();
        boolean toUpdate = userUpdater.updateObject(currentUser, user, currentUsername);

        if (toUpdate) {
            userRepository.save(currentUser);
            //sendUpdateEmail(currentUser);
        }

        return toUpdate;
    }

    @Override
    public boolean updateRole(Long id, UserRoles role) throws IllegalAccessException, DuplicateEmailException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con id: " + id));

        if (!currentUser.getUserRoles().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Solo i Super Admin sono autorizzati a modificare i ruoli!");
        }

        if (userToUpdate.getUserRoles() == role) {
            return false;
        }

        userToUpdate.setUserRoles(role);
        userRepository.save(userToUpdate);
        return true;
    }

    @Override
    public boolean updatePassword(Long id, String currentPassword, String newPassword) throws IllegalAccessException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con id: " + id));

        if (!currentUser.getId().equals(userToUpdate.getId())) {
            throw new IllegalAccessException("Non sei autorizzato a modificare la password di altri utenti!");
        }

        if (!passwordEncoder.matches(currentPassword, userToUpdate.getPassword())) {
            throw new IllegalAccessException("Password corrente errata!");
        }

        if (passwordEncoder.matches(newPassword, userToUpdate.getPassword())) {
            return false;
        }

        userToUpdate.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userToUpdate);

        // sendPasswordChangeEmail(userToUpdate);

        return true;
    }

    @Override
    public UserSummaryDTO getUserByUsername(String username)

            throws UserNotFoundException {

        return userRepository.findByUsername(username)
                .map(user -> new UserSummaryDTO(user))
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato!"));

    }

}
