package org.polimi.nexbuy.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.LockAcquisitionException;
import org.polimi.nexbuy.dto.output.category.CategorySummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.model.Category;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;
import org.polimi.nexbuy.repository.CategoryRepository;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.CategoryService;
import org.polimi.nexbuy.utils.ObjectUpdater;
import org.polimi.nexbuy.utils.SecurityUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("All")
@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public void createCategory(Category category) throws DataAccessServiceException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Categoria già esistente con nome: " + category.getName());
        }

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN)
                || currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))) {
            throw new SecurityException("Solo Admin e Super Admin possono modificare le categorie!");
        }

        var categoryToInsert = Category.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();

        try {
            categoryRepository.save(categoryToInsert);
        } catch (ConstraintViolationException | DataIntegrityViolationException | TransientObjectException |
                 LockAcquisitionException e) {
            throw new DataAccessServiceException("Errore durante il salvataggio della categoria!");
        }
    }

    @Override
    public boolean updateCategory(Category category, String name) throws IllegalAccessException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN) || currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))) {
            throw new SecurityException("Solo Admin e Super Admin possono modificare le categorie!");
        }

        Category categoryToUpdate = categoryRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Categoria non trovata con nome: " + name));

        if (category.getName() != null && !category.getName().equals(categoryToUpdate.getName())) {
            if (categoryRepository.existsByName(category.getName())) {
                throw new RuntimeException("Esiste già una categoria con nome: " + category.getName());
            }
        }

        ObjectUpdater<Category> categoryUpdater = new ObjectUpdater<>();
        boolean toUpdate = categoryUpdater.updateObject(categoryToUpdate, category, currentUsername);

        if (toUpdate) {
            categoryRepository.save(categoryToUpdate);
        }

        return toUpdate;
    }

    @Override
    public void deleteCategory(String name) throws DataAccessServiceException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN) || currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))) {
            throw new SecurityException("Solo Admin e Super Admin possono modificare le categorie!");
        }

        Category categoryToDelete = categoryRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Categoria non trovata con nome: " + name));

        try {
            categoryRepository.delete(categoryToDelete);
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante l’eliminazione del prodotto!");
        }

    }

    @Override
    public CategorySummaryDTO getCategoryByName(String name) throws DataAccessServiceException, IllegalAccessException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Non sei autorizzato a visualizzare questi dati!");
        }

        Category requestedCategory = categoryRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Categoria non trovata con nome: " + name));

        return new CategorySummaryDTO(requestedCategory);
    }

    @Override
    public List<CategorySummaryDTO> getAllCategories() throws DataAccessServiceException, IllegalAccessException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Solo Admin e Super Admin possono visualizzare tutti gli utenti!");
        }

        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream()
                    .map(CategorySummaryDTO::new)
                    .toList();
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante il recupero degli utenti!");
        }
    }
}
