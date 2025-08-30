package org.polimi.nexbuy.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.LockAcquisitionException;
import org.polimi.nexbuy.dto.output.product.ProductSummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.exception.customExceptions.product.ProductNotFoundException;
import org.polimi.nexbuy.exception.customExceptions.user.UserNotFoundException;
import org.polimi.nexbuy.model.Product;
import org.polimi.nexbuy.model.SubCategory;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;
import org.polimi.nexbuy.repository.ProductRepository;
import org.polimi.nexbuy.repository.SubCategoryRepository;
import org.polimi.nexbuy.repository.UserRepository;
import org.polimi.nexbuy.service.ProductService;
import org.polimi.nexbuy.utils.ObjectUpdater;
import org.polimi.nexbuy.utils.SecurityUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SuppressWarnings("All")
@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;

    private final ImageServiceImpl imageService;

    @Override
    public void createProduct(Product product, List<MultipartFile> files, Long subCategoryId) throws DataAccessServiceException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("SubCategory non trovata con id: " + subCategoryId));

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN)
                || currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))
                || currentUser.getRole().equals(UserRoles.ROLE_SELLER)) {
            throw new SecurityException("Solo Admin e Super Admin possono creare prodotti!");
        }

        var productToInsert = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .manufacturer(product.getManufacturer())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .subCategory(subCategory)
                .seller(currentUser)
                .build();

        try {
            productRepository.save(productToInsert);
            if (files != null && !files.isEmpty()) {
                imageService.saveProductImages(files, productToInsert);
            }
        } catch (ConstraintViolationException | DataIntegrityViolationException | TransientObjectException |
                 LockAcquisitionException e) {
            throw new DataAccessServiceException("Errore durante il salvataggio del prodotto!");
        }
    }

    @Override
    public boolean updateProduct(Long id, Product product, List<MultipartFile> files, Long subCategoryId, List<Long> deleteImages) throws ProductNotFoundException, DataAccessServiceException, IllegalAccessException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN) || currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))) {
            throw new SecurityException("Solo Admin e Super Admin possono creare prodotti!");
        }

        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Prodotto non trovato con id: " + id));

        boolean subCategoryUpdated = false;
        if (subCategoryId != null) {
            if (productToUpdate.getSubCategory() == null ||
                    !productToUpdate.getSubCategory().getId().equals(subCategoryId)) {

                SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                        .orElseThrow(() -> new RuntimeException("SubCategory non trovata con id: " + subCategoryId));
                productToUpdate.setSubCategory(subCategory);
                subCategoryUpdated = true;
            }
        }

        if (deleteImages != null && !deleteImages.isEmpty()) {
            imageService.deleteImages(deleteImages);
        }

        if (files != null && !files.isEmpty()) {
            imageService.saveProductImages(files, productToUpdate);
        }

        ObjectUpdater<Product> productUpdater = new ObjectUpdater<>();
        boolean otherFieldsUpdated = productUpdater.updateObject(productToUpdate, product, currentUsername);

        if (otherFieldsUpdated || subCategoryUpdated) {
            productRepository.save(productToUpdate);
            return true;
        }

        return false;

    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException, DataAccessServiceException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utente non autenticato"));

        if (!(currentUser.getRole().equals(UserRoles.ROLE_ADMIN) || currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN))) {
            throw new SecurityException("Solo Admin e Super Admin possono eliminare prodotti!");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Prodotto non trovato con id: " + id));

        try {
            productRepository.delete(product);
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante l’eliminazione del prodotto!");
        }

    }

    @Override
    public ProductSummaryDTO getProductById(Long id) throws ProductNotFoundException, IllegalAccessException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        Product requestedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Prodotto non trovato con id: " + id));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Non sei autorizzato a visualizzare questi dati!");
        }

        return new ProductSummaryDTO(requestedProduct);
    }

    @Override
    public List<ProductSummaryDTO> getAllProducts() throws DataAccessServiceException, IllegalAccessException {

        String currentUsername = SecurityUtils.getCurrentUsername();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Utente non registrato!"));

        if (!currentUser.getRole().equals(UserRoles.ROLE_ADMIN) &&
                !currentUser.getRole().equals(UserRoles.ROLE_SUPER_ADMIN)) {
            throw new IllegalAccessException("Solo Admin e Super Admin possono visualizzare tutti gli utenti!");
        }

        try {
            List<Product> products = productRepository.findAll();
            return products.stream()
                    .map(ProductSummaryDTO::new)
                    .toList();
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante il recupero degli utenti!");
        }
    }
}
