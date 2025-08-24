package org.polimi.nexbuy.dto.mapper.product;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.product.ProductDTO;
import org.polimi.nexbuy.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product productDTOToProduct(InputDTO productDTO) {
        if (!(productDTO instanceof ProductDTO productData))
            throw new ClassCastException("Errore di conversione");
        else {
            Product product = new Product();

            product.setName(productData.getName());
            product.setDescription(productData.getDescription());
            product.setManufacturer(productData.getManufacturer());
            product.setPrice(productData.getPrice());
            product.setQuantity(productData.getQuantity());
            return product;
        }
    }

}
