package org.acme.Validator.logica;

import org.acme.Validator.Anotacoes.CampoNome;

import java.lang.reflect.Field;

public class ValidatorUtils {

    public static String getCampoName(Field field){
        return field.getAnnotation(CampoNome.class) != null ? field.getAnnotation(CampoNome.class).value() : field.getName();
    }
}
