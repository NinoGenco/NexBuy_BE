package org.polimi.nexbuy.controller;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.model.products.Product;
import org.polimi.nexbuy.service.def.products.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ecommerce/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int Id) {
        Product product = productService.getProductById(Id);
        return (product != null)
                ? ResponseEntity.ok(product)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = productService.addProduct(product);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long Id, @RequestBody Product product) {
        product.setId(Id);
        Product updated = productService.updateProduct(product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int Id) {
        productService.deleteProduct(Id);
        return ResponseEntity.noContent().build();
    }
}
