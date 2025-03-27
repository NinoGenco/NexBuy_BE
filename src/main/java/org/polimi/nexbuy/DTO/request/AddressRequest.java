package org.polimi.nexbuy.DTO.request;

import lombok.Data;

@Data
public class AddressRequest {

    private String street;
    private String city;
    private String state;
    private String zipCode; //CAP
    private String country;
}
