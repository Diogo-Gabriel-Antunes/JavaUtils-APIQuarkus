package org.acme.Validator.Interface;

import org.acme.Util.InterfacesUtil.DTO;

import java.lang.reflect.Field;

public interface ValidadorInterface {
    public void validador(Field field,DTO dto);
}
