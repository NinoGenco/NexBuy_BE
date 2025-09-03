package org.polimi.nexbuy.dto.mapper.category;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category categoryDTOToCategory(InputDTO categoryDTO) {
        if (!(categoryDTO instanceof Category categoryData))
            throw new ClassCastException("Errore di conversione");
        else {
            Category category = new Category();

            category.setName(categoryData.getName());
            category.setDescription(categoryData.getDescription());
            return category;
        }
    }

}
