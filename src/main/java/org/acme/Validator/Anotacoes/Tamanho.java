package org.acme.Validator.Anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Tamanho {
    long maximo() default 9223372036854775807L;
    int minimo() default -2147483648;
}
