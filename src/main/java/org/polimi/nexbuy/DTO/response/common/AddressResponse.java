package org.polimi.nexbuy.DTO.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private long id;
    private String street;
    private String city;
    private String state;
    private String zipCode; //CAP
    private String country;
}
