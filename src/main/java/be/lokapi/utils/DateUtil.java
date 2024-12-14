package be.lokapi.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {


    public static LocalDate toLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public static Date toDate(LocalDate localDate){
        return java.sql.Date.valueOf(localDate);
    }
}
