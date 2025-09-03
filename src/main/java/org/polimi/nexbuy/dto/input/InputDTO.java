package org.polimi.nexbuy.dto.input;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.polimi.nexbuy.dto.input.category.CategoryDTO;
import org.polimi.nexbuy.dto.input.category.UpdateCategoryDTO;
import org.polimi.nexbuy.dto.input.product.ProductDTO;
import org.polimi.nexbuy.dto.input.product.UpdateProductDTO;
import org.polimi.nexbuy.dto.input.user.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "CustomType")
@JsonSubTypes({

        //user
        @JsonSubTypes.Type(value = RegistrationDTO.class, name = "registration"),
        @JsonSubTypes.Type(value = AuthenticationDTO.class, name = "authentication"),
        @JsonSubTypes.Type(value = UpdateUserDTO.class, name = "update_user"),
        @JsonSubTypes.Type(value = UpdateUserPasswordDTO.class, name = "update_password"),
        @JsonSubTypes.Type(value = UpdateUserRoleDTO.class, name = "update_role"),

        @JsonSubTypes.Type(value = ProductDTO.class, name = "create_product"),
        @JsonSubTypes.Type(value = UpdateProductDTO.class, name = "update_product"),

        @JsonSubTypes.Type(value = CategoryDTO.class, name = "create_category"),
        @JsonSubTypes.Type(value = UpdateCategoryDTO.class, name = "update_category"),
})
public interface InputDTO {
}
