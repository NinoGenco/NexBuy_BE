package org.polimi.nexbuy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe che rappresenta il messaggio di errore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private LocalDateTime timestamp;
    private Integer status;
    private String error, message, path;
}
