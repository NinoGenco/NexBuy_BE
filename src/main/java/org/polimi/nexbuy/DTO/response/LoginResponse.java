//creo questa classe per gestire la ridsposta che arriva dal backand al frontand

package org.polimi.nexbuy.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String role;

}