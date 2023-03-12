package org.acme.Validator.logica;

import org.acme.Util.InterfacesUtil.DTO;
import org.acme.Validator.Anotacoes.DataValida;
import org.acme.Validator.Anotacoes.PodeSerNull;
import org.acme.Validator.Anotacoes.Tamanho;
import org.acme.Validator.Excecao.DataValidaException;
import org.acme.Validator.Excecao.PodeSerNullException;
import org.acme.Validator.Excecao.TamanhoException;
import org.acme.Validator.Interface.ValidadorInterface;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DataValidator implements ValidadorInterface {


    @Override
    public void validador(Field field,DTO dto) {
        String campoNome = ValidatorUtils.getCampoName(field);
        try{
            Object attribute = field.get(dto);
            DataValida dataValida = field.getAnnotation(DataValida.class);
            if(dataValida != null && attribute != null){
                try{
                    LocalDate.parse(String.valueOf(attribute));
                }catch (DateTimeParseException e){
                    try{
                        LocalDateTime.parse(String.valueOf(attribute));
                    }catch (DateTimeParseException v){
                        DataValidaException exception = new DataValidaException();
                        exception.add("O campo que foi informado esta com a data invalido", campoNome);
                        exception.lancarErro();
                    }
                }
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
}
