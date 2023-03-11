package org.acme.Exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UtilException extends RuntimeException {
    private List<Erro> erro;

    public void add(String excecao){
        if(erro == null){
            erro = new ArrayList<>();
        }
        Erro newError = new Erro();
        newError.setErro(excecao);
        erro.add(newError);
    }
    public void lancaErro(){
        if(!erro.isEmpty()){
            throw new UtilException();
        }
    }
}
