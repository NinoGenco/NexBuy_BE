package org.polimi.nexbuy.dto.output.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.model.enums.UserRoles;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private UserRoles userRoles;

    public UserSummaryDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.userRoles = user.getUserRoles();
    }
}
