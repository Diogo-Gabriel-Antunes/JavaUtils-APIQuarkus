package org.acme.Util.PrimitiveUtil;

import org.acme.Exception.UtilException;

public class FloatUtil {
    public static Float parseFromString(String str){
        try{
            return Float.parseFloat(str);
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }
    public static Float parseFromLong(Long num){
        try{
            return Float.parseFloat(String.valueOf(num));
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }
    public static Float parseFromFloat(Float num){
        try{
            return Float.parseFloat(String.valueOf(num));
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }
    public static Float parseFromInteger(Integer num){
        try{
            return Float.parseFloat(String.valueOf(num));
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }
    public static Float parseFromDouble(Double num){
        try{
            return Float.parseFloat(String.valueOf(num));
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }

    public static Boolean isValidDifZero(Float num){
        return num != null && num != 0;
    }
    public static Boolean isValid(Float num){
        return num != null;
    }
}
