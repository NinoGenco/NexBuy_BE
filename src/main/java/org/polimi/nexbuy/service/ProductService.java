package org.polimi.nexbuy.service;

import org.polimi.nexbuy.dto.output.product.ProductSummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.exception.customExceptions.product.ProductNotFoundException;
import org.polimi.nexbuy.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    void createProduct(Product product, List<MultipartFile> files, Long subCategoryId) throws DataAccessServiceException;

    boolean updateProduct(Long id, Product product, List<MultipartFile> files, Long subCategoryId, List<Long> deleteImages) throws ProductNotFoundException, DataAccessServiceException, IllegalAccessException;

    void deleteProduct(Long id) throws ProductNotFoundException, DataAccessServiceException;

    ProductSummaryDTO getProductById(Long id) throws ProductNotFoundException, IllegalAccessException;

    List<ProductSummaryDTO> getAllProducts() throws DataAccessServiceException, IllegalAccessException;

}
