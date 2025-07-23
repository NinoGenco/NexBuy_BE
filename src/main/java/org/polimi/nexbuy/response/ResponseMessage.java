package org.polimi.nexbuy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe che rappresenta un messaggio di risposta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private String message;

}
