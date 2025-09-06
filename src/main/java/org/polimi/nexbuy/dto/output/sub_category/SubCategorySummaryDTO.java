package org.polimi.nexbuy.dto.output.sub_category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.model.SubCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategorySummaryDTO {

    private String name;
    private String description;

    public SubCategorySummaryDTO(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.description = subCategory.getDescription();
    }

}
