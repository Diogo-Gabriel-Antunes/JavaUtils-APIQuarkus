package org.acme.Validator.logica;

import org.acme.Util.InterfacesUtil.DTO;
import org.acme.Validator.Anotacoes.DataValida;
import org.acme.Validator.Anotacoes.VazioString;
import org.acme.Validator.Excecao.DataValidaException;
import org.acme.Validator.Excecao.VazioStringException;
import org.acme.Validator.Interface.ValidadorInterface;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class VazioStringValidator implements ValidadorInterface {


    @Override
    public void validador(Field field,DTO dto) {
        String campoNome = ValidatorUtils.getCampoName(field);
        try{
            Object attribute = field.get(dto);
            VazioString vazioString = field.getAnnotation(VazioString.class);
            if(vazioString != null && attribute != null){
                String str = String.valueOf(attribute);
                if(str.length() == 0){
                    VazioStringException exception = new VazioStringException();
                    exception.add("O campo esta vazio",campoNome);
                    exception.lancarErro();
                }
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
}
