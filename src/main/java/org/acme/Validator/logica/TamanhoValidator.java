package org.acme.Validator.logica;

import org.acme.Util.InterfacesUtil.DTO;
import org.acme.Validator.Anotacoes.CampoNome;
import org.acme.Validator.Anotacoes.PodeSerNull;
import org.acme.Validator.Anotacoes.Tamanho;
import org.acme.Validator.Excecao.PodeSerNullException;
import org.acme.Validator.Excecao.TamanhoException;
import org.acme.Validator.Interface.ValidadorInterface;

import java.lang.reflect.Field;

public class TamanhoValidator implements ValidadorInterface {


    @Override
    public void validador(Field field,DTO dto) {
        String campoNome = ValidatorUtils.getCampoName(field);
        try{
            String str = String.valueOf(field.get(dto));
            if(str.equals("null")){
                PodeSerNull podeSerNull = field.getAnnotation(PodeSerNull.class);
                if(podeSerNull != null){
                    PodeSerNullException exception = new PodeSerNullException();
                    exception.add("O campo esta nullo", campoNome);
                    exception.lancarErro();
                }
            }
            Tamanho tamanho = field.getAnnotation(Tamanho.class);
            if(tamanho != null){
                TamanhoException exception = new TamanhoException();
                if(str.length() < tamanho.minimo() && str.length() > tamanho.maximo()){
                    exception.add("O campo não corresponde nem a o tamanho minimo nem ao tamanho maximo",campoNome);
                }else if(str.length() < tamanho.minimo()){
                    exception.add("Campo não correspondo ao valor minimo de caracteres",campoNome);
                }else if(str.length() > tamanho.maximo()){
                    exception.add("Campo não correspondo ao valor maximo de caracteres",campoNome);
                }
                exception.lancarErro();
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
}
