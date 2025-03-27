package org.polimi.nexbuy.service.impl;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.exception.ProductNotFoundException;
import org.polimi.nexbuy.model.Category;
import org.polimi.nexbuy.model.Product;
import org.polimi.nexbuy.model.SubCategory;
import org.polimi.nexbuy.repository.ProductRepository;
import org.polimi.nexbuy.service.def.CategoryService;
import org.polimi.nexbuy.service.def.ProductService;
import org.polimi.nexbuy.service.def.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, SubCategoryService subCategoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Prodotto con ID" + productId + " non trovato"));
    }

    public void deleteProduct(long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Prodotto con ID" + productId + " non trovato per l'eliminazione");
        }
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException("Prodotto con ID" + product.getId() + " non trovato per l'aggiornamento");
        }
        return productRepository.save(product);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
