package org.acme.Validator.Excecao;

import org.acme.exceptions.Validacao;

import java.util.ArrayList;
import java.util.List;

public class PodeSerNullException extends RuntimeException{

    private List<ValidacaoValidator> erro;

    public void add(String validacao,String campo){
        if(erro == null){
            erro = new ArrayList<>();
        }
        ValidacaoValidator newValidacao = new ValidacaoValidator();
        newValidacao.setErro(validacao);
        newValidacao.setCampo(campo);
        erro.add(newValidacao);
    }

    public void lancarErro(){
        if(!erro.isEmpty()){
            throw new PodeSerNullException();
        }
    }
}
