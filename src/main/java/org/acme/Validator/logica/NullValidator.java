package org.acme.Validator.logica;

import org.acme.Util.InterfacesUtil.DTO;
import org.acme.Validator.Anotacoes.DataValida;
import org.acme.Validator.Anotacoes.PodeSerNull;
import org.acme.Validator.Excecao.DataValidaException;
import org.acme.Validator.Excecao.PodeSerNullException;
import org.acme.Validator.Interface.ValidadorInterface;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class NullValidator implements ValidadorInterface {


    @Override
    public void validador(Field field,DTO dto) {
        String campoNome = ValidatorUtils.getCampoName(field);
        try{
            Object attribute = field.get(dto);
            PodeSerNull podeSerNull = field.getAnnotation(PodeSerNull.class);
            if(podeSerNull != null && attribute == null){
                PodeSerNullException exception = new PodeSerNullException();
                exception.add("O campo esta nullo", campoNome);
                exception.lancarErro();
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
}
