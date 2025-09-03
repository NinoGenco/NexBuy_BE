package org.polimi.nexbuy.dto.output.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.model.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySummaryDTO {

    private String name;
    private String description;

    public CategorySummaryDTO(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
    }

}
