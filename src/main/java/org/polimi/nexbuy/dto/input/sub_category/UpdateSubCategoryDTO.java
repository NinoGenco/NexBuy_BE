package org.polimi.nexbuy.dto.input.sub_category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubCategoryDTO implements InputDTO {

    private String name;

    private String description;

    private String categoryName;

}
