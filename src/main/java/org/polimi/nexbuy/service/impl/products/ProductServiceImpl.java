package org.polimi.nexbuy.service.impl.products;

import org.polimi.nexbuy.exception.products.ProductNotFoundException;
import org.polimi.nexbuy.model.products.Product;
import org.polimi.nexbuy.repository.products.ProductRepository;
import org.polimi.nexbuy.service.def.products.CategoryService;
import org.polimi.nexbuy.service.def.products.ProductService;
import org.polimi.nexbuy.service.def.products.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
