package org.polimi.nexbuy.dto.input;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.polimi.nexbuy.dto.input.user.AuthenticationDTO;
import org.polimi.nexbuy.dto.input.user.RegistrationDTO;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "CustomType")
@JsonSubTypes({

        //user
        @JsonSubTypes.Type(value = RegistrationDTO.class, name = "registration"),
        @JsonSubTypes.Type(value = AuthenticationDTO.class, name = "authentication"),
})
public interface InputDTO {
}
