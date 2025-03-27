package org.polimi.nexbuy.DTO.request;

import lombok.Data;

import java.time.LocalDate;

@Data

public class RegisterRequest {

        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String telephone;
        private String address;
        private String city;
        private String state;
}
