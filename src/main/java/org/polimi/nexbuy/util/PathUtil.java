package org.polimi.nexbuy.util;

import org.polimi.nexbuy.exception.products.CategoryAlreadyExistsException;

import java.util.List;

public class PathUtil {

    public static void controlloStringhe(List<String> data) {
        if(data.stream().anyMatch(String::isBlank)) throw new CategoryAlreadyExistsException("Stringa non valida");
        for (String s : data) {
            if (s == null || s.isBlank()) {
                throw new CategoryAlreadyExistsException("Stringa non valida");
            }
        }
    }

    public static void controlloNumeri(List<Number> numeri) {
        for (Number n : numeri) {
            if (n instanceof Integer && (n.intValue() < 1 || n.intValue() > 5)) {
                throw new CategoryAlreadyExistsException("Numero non valido");
            } else if (n instanceof Long && n.longValue() < 1) {
                throw new CategoryAlreadyExistsException("Id non valido");
            }
        }
    }
}
