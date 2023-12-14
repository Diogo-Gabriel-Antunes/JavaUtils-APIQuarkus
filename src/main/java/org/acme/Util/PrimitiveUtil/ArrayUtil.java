package org.acme.Util.PrimitiveUtil;

import java.util.List;

public class ArrayUtil {
    public static  Boolean validaArray(List array){
        return array != null &&  !array.isEmpty() && array instanceof List;
    }
}
