package org.polimi.nexbuy.dto.output.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.model.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSummaryDTO {

    private Long id;
    private String name;
    private String description;
    private String manufacturer;
    private Double price;
    private Integer quantity;

    public ProductSummaryDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.manufacturer = product.getManufacturer();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }

}
