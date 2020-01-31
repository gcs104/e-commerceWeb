package com.whz.commerce.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateTimeUtil {



    //str->Date
    //Date->str
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";



    public static LocalDate strToDate(String dateTimeStr,String formatStr){

        DateTimeFormatter df = DateTimeFormatter.ofPattern(formatStr);
        //转换日期格式
        LocalDate date2 = LocalDate.parse(dateTimeStr,df);

        return date2;
    }

    public static String dateToStr(LocalDate date,String formatStr){
        if(date == null){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formatStr);

        LocalDate date2 = LocalDate.parse(date.toString(),df);
        return date2.toString();
    }





    public static void main(String[] args) {
        System.out.println(DateTimeUtil.dateToStr(LocalDate.now(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtil.strToDate("","yyyy-MM-dd HH:mm:ss"));

    }


}