package org.polimi.nexbuy.utils;

import java.time.LocalDateTime;

/**
 * Interfaccia per gli oggetti che possono essere aggiornati
 */
public interface Updatable {

    void setUpdateAt(LocalDateTime updateAt);

    void setUpdatedBy(String updatedBy);

}