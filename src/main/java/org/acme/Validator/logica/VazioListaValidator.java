package org.acme.Validator.logica;

import org.acme.Util.InterfacesUtil.DTO;
import org.acme.Validator.Anotacoes.VazioLista;
import org.acme.Validator.Excecao.VazioListaException;
import org.acme.Validator.Interface.ValidadorInterface;

import java.lang.reflect.Field;
import java.util.List;

public class VazioListaValidator implements ValidadorInterface {


    @Override
    public void validador(Field field, DTO dto) {
        String campoNome = ValidatorUtils.getCampoName(field);
        try {
            Object attribute = field.get(dto);
            VazioLista vazioLista = field.getAnnotation(VazioLista.class);
            if (attribute != null && vazioLista != null) {
                if (attribute instanceof List) {
                    List list = (List) attribute;
                    if (list.size() == 0) {
                        VazioListaException exception = new VazioListaException();
                        exception.add("A campo informado é um lista e não pode estar vazia", campoNome);
                        exception.lancarErro();
                    }
                }

            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
