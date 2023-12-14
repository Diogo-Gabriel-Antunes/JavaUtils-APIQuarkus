package org.acme.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParserUtil {
    public static Gson parser = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter2())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public static String toJson(Object o) {
        return parser.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return parser.fromJson(json, clazz);
    }

    public static <T> List<T> listToClass(String json, Class<T> tClass) {
        Collection oldLIst = parser.fromJson(json, List.class);
        List<T> list = new ArrayList<>();
        if (CollectionsUtil.isValid(oldLIst)) {
            if (tClass != null) {
                for (Object o : oldLIst) {
                    String jsonNewObject = parser.toJson(o);
                    T t = parser.fromJson(jsonNewObject, tClass);
                    list.add(t);
                }
                return list;
            }else{
                return oldLIst.stream().toList();
            }
        }

        return null;
    }
}
