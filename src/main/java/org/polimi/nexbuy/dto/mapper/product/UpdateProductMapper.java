package org.polimi.nexbuy.dto.mapper.product;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.product.UpdateProductDTO;
import org.polimi.nexbuy.model.Product;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductMapper {

    public Product productDTOToProduct(InputDTO productDTO) {
        if (!(productDTO instanceof UpdateProductDTO productData))
            throw new ClassCastException("Errore di conversione");
        else {
            Product product = new Product();

            product.setId(product.getId());
            product.setName(productData.getName());
            product.setDescription(productData.getDescription());
            product.setManufacturer(productData.getManufacturer());
            product.setPrice(productData.getPrice());
            product.setQuantity(productData.getQuantity());
            return product;
        }
    }

}
