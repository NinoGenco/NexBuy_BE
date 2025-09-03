package org.polimi.nexbuy.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Classe di utilità per l'aggiornamento degli oggetti
 * @param <T> tipo dell'oggetto da aggiornare
 */
public class ObjectUpdater<T> {

    /**
     * Aggiorna un oggetto con i valori di un nuovo oggetto
     * @param object oggetto da aggiornare
     * @param newObject nuovo oggetto con i valori aggiornati
     * @param updatedBy (non usato più, mantenuto solo per compatibilità con la firma)
     * @return true se l'oggetto è stato aggiornato, false altrimenti
     * @throws IllegalAccessException se si verifica un errore nell'accesso ai campi dell'oggetto
     */
    public boolean updateObject(T object, T newObject, String updatedBy) throws IllegalAccessException {
        boolean isUpdate = false;

        Field[] fields = newObject.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            Object oldValue = field.get(object);
            Object fieldValue = field.get(newObject);

            if (fieldValue != null && !fieldValue.equals(oldValue) && !(fieldValue instanceof Collection)) {
                field.set(object, fieldValue);
                isUpdate = true;
            }
        }

        return isUpdate;
    }
}
