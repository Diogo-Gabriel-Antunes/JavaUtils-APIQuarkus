package org.acme.Util;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class LocalDateTimeUtil {

    public static LocalDateTime deYYYYMMDDTHHMMSSSSSZ(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDateTime.parse(str, formatter);
    }
    public static LocalDateTime deYYYYMMDD(String str){
        LocalDate localDate = LocalDateUtil.deYYYYMMDD(str);
        return LocalDateTime.of(localDate, LocalTime.now());
    }
    public static String retornaYYYYMMDDTHHMMSS(LocalDateTime date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return format.format(date);
    }
}
