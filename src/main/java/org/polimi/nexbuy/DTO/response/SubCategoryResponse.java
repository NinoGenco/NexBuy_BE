package org.polimi.nexbuy.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubCategoryResponse {
    private long id;
    private String name;
    private String categoryName;

    public SubCategoryResponse(String name) {
        this.name = name;
    }
}
