package org.polimi.nexbuy.DTO.request.products;

import lombok.Data;

@Data
public class SubCategoryRequest {

    private String name;
    private long categoryId;
}
