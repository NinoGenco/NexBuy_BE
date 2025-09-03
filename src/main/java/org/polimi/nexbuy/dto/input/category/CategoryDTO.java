package org.polimi.nexbuy.dto.input.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements InputDTO {

    private String name;

    private String description;

}
