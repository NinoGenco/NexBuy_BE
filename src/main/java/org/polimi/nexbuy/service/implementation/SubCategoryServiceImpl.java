package org.polimi.nexbuy.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.LockAcquisitionException;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.model.Category;
import org.polimi.nexbuy.model.SubCategory;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;
import org.polimi.nexbuy.repository.CategoryRepository;
import org.polimi.nexbuy.repository.SubCategoryRepository;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.SubCategoryService;
import org.polimi.nexbuy.utils.ObjectUpdater;
import org.polimi.nexbuy.utils.SecurityUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@SuppressWarnings("All")
@Service
@Transactional
@AllArgsConstructor
public class SubCategoryServiceImpl {

    /*private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public void createSubCategory(SubCategory subCategory, String categoryName) throws DataAccessServiceException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN)
                || currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))) {
            throw new SecurityException("Solo Admin e Super Admin possono creare sottocategorie!");
        }

        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con nome: " + categoryName));

        if (subCategoryRepository.existsByNameAndCategory(subCategory.getName(), category)) {
            throw new RuntimeException("Sottocategoria già esistente con nome: " + subCategory.getName());
        }

        var subCategoryToInsert = SubCategory.builder()
                .name(subCategory.getName())
                .description(subCategory.getDescription())
                .category(category)
                .build();

        try {
            subCategoryRepository.save(subCategoryToInsert);
        } catch (ConstraintViolationException | DataIntegrityViolationException |
                 TransientObjectException | LockAcquisitionException e) {
            throw new DataAccessServiceException("Errore durante il salvataggio della sottocategoria!");
        }
    }

    @Override
    public boolean updateSubCategory(SubCategory subCategory, String name) throws IllegalAccessException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN) ||
                currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))) {
            throw new SecurityException("Solo Admin e Super Admin possono modificare le sottocategorie!");
        }

        SubCategory subCategoryToUpdate = subCategoryRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Sottocategoria non trovata con nome: " + name));

        if (subCategory.getName() != null && !subCategory.getName().equals(subCategoryToUpdate.getName())) {
            if (subCategoryRepository.existsByNameAndCategory(subCategory.getName(), subCategoryToUpdate.getCategory())) {
                throw new RuntimeException("Esiste già una sottocategoria con nome: " + subCategory.getName());
            }
        }

        ObjectUpdater<SubCategory> updater = new ObjectUpdater<>();
        boolean toUpdate = updater.updateObject(subCategoryToUpdate, subCategory, currentUsername);

        if (toUpdate) {
            subCategoryRepository.save(subCategoryToUpdate);
        }

        return toUpdate;
    }

    @Override
    public void deleteSubCategory(String name) throws DataAccessServiceException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN) ||
                currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))) {
            throw new SecurityException("Solo Admin e Super Admin possono eliminare le sottocategorie!");
        }

        SubCategory subCategoryToDelete = subCategoryRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Sottocategoria non trovata con nome: " + name));

        try {
            subCategoryRepository.delete(subCategoryToDelete);
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante l’eliminazione della sottocategoria!");
        }
    }

    @Override
    public SubCategorySummaryDTO getSubCategoryByName(String name) throws DataAccessServiceException, IllegalAccessException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Non sei autorizzato a visualizzare questi dati!");
        }

        SubCategory requestedSubCategory = subCategoryRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Sottocategoria non trovata con nome: " + name));

        return new SubCategorySummaryDTO(requestedSubCategory);
    }

    @Override
    public List<SubCategorySummaryDTO> getAllSubCategories() throws DataAccessServiceException, IllegalAccessException {
        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Solo Admin e Super Admin possono visualizzare tutte le sottocategorie!");
        }

        try {
            List<SubCategory> subCategories = subCategoryRepository.findAll();
            return subCategories.stream()
                    .map(SubCategorySummaryDTO::new)
                    .toList();
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante il recupero delle sottocategorie!");
        }
    }*/
}
