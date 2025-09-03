package org.polimi.nexbuy.dto.input.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO implements InputDTO {

    private String name;

    private String description;

    private String manufacturer;

    private Double price;

    private Integer quantity;

    private Long subCategoryId;

    private List<Long> deleteImages;

}
