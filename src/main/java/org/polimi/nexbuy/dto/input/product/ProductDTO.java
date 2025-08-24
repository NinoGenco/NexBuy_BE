package org.polimi.nexbuy.dto.input.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements InputDTO {

    private String name;

    private String description;

    private String manufacturer;

    private Double price;

    private Integer quantity;

}
