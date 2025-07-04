//creo questa classe per gestire la ridsposta che arriva dal backand al frontand

package org.polimi.nexbuy.DTO.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String role;

}