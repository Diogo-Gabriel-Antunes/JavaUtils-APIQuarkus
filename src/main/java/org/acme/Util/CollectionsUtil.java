package org.acme.Util;

import java.util.Collection;

public class CollectionsUtil {

    public static Boolean isValid(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    public static Boolean isValidEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

}
