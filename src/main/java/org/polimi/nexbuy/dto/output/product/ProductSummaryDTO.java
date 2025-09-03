package org.polimi.nexbuy.dto.output.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.dto.output.image.ImageSummaryDTO;
import org.polimi.nexbuy.model.Product;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<ImageSummaryDTO> images;

    public ProductSummaryDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.manufacturer = product.getManufacturer();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.images = product.getImages() != null
                ? product.getImages().stream().map(ImageSummaryDTO::new).collect(Collectors.toList())
                : null;
    }

}
