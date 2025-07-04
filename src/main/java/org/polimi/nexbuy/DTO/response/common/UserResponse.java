package org.polimi.nexbuy.DTO.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.model.enums.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String telephone;
    private Role role;
}
