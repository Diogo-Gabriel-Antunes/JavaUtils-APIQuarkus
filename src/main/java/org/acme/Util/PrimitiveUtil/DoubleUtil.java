package org.acme.Util.PrimitiveUtil;

import org.acme.Exception.UtilException;

public class DoubleUtil {

    public static Double parseFromString(String str){
        try{
            return Double.parseDouble(str);
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }
    public static Double parseFromLong(Long num){
        try{
            return Double.parseDouble(String.valueOf(num));
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }
    public static Double parseFromFloat(Float num){
        try{
            return Double.parseDouble(String.valueOf(num));
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }
    public static Double parseFromInteger(Integer num){
        try{
            return Double.parseDouble(String.valueOf(num));
        }catch (Throwable t){
            t.printStackTrace();
            UtilException utilException = new UtilException();
            utilException.add("Erro na conversão de valores, favor informar o suporte");
            utilException.lancaErro();
            return null;
        }
    }
    public static Boolean isValidDifZero(Double num){
        return num != null && num != 0;
    }
    public static Boolean isValid(Double num){
        return num != null;
    }
}
