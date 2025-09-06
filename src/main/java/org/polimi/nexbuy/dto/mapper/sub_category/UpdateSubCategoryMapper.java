package org.polimi.nexbuy.dto.mapper.sub_category;

import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.model.SubCategory;
import org.springframework.stereotype.Component;

@Component
public class UpdateSubCategoryMapper {

    public SubCategory subCategoryDTOToSubCategory(InputDTO subCategoryDTO) {
        if (!(subCategoryDTO instanceof SubCategory subCategoryData))
            throw new ClassCastException("Errore di conversione");
        else {
            SubCategory subCategory = new SubCategory();

            subCategory.setName(subCategoryData.getName());
            subCategory.setDescription(subCategoryData.getDescription());
            return subCategory;
        }
    }

}
