package org.polimi.nexbuy.utils;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

/**
 * Classe di utilità per l'estrazione delle informazioni relative ai mapping delle richieste
 */
public class RequestMappingUtils {

    /**
     * Estrae il percorso del mapping di una richiesta
     * @param handlerMethod metodo del controller
     * @return percorso del mapping
     */
    public static String extractPath(HandlerMethod handlerMethod) {
        RequestMapping classMapping = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), RequestMapping.class);
        RequestMapping methodMapping = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RequestMapping.class);

        String classPath = (classMapping != null && classMapping.value().length > 0) ? classMapping.value()[0] : "";
        String methodPath = (methodMapping != null && methodMapping.value().length > 0) ? methodMapping.value()[0] : "";

        return classPath + methodPath;
    }

    /**
     * Estrae il nome della classe del controller
     * @param handlerMethod metodo del controller
     * @return nome della classe
     */
    public static String extractClassName(HandlerMethod handlerMethod) {
        return handlerMethod.getBeanType().getSimpleName();
    }

    /**
     * Estrae il nome del metodo del controller
     * @param handlerMethod metodo del controller
     * @return nome del metodo
     */
    public static String extractMethodName(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().getName();
    }

}
