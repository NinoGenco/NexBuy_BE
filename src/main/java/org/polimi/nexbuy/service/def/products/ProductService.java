package org.polimi.nexbuy.service.def.products;

import org.polimi.nexbuy.model.products.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(long productId);
    void deleteProduct(long productId);
    Product updateProduct(Product product);
    Product addProduct(Product product);
}
