package by.pvt.util;

import by.pvt.constants.ConstantsValues;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Class for parsing date to different types
 */
public class DateFormatUtil {
    /**
     * @param s строка из формы   для парсинга в long
     * @return long
     */
    public static long dateFormatterFromString(String s) {
        long date = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = format.parse(s);
            date = d.getTime();
        } catch (ParseException e) {
            //TODO hiding exceptions is a bad practise
            e.printStackTrace();
        }
        return date;
    }

    /**
     * метод парсит из long в стороку
     *
     * @param date дата в формате long
     * @return дату в формате String
     */
    public static String dateFormatterFromLong(long date) {
        String strDate = "";
        Date dateOut = new Date(date);

        SimpleDateFormat inputDate = new SimpleDateFormat(ConstantsValues.DATE_PATTERN);

        strDate = inputDate.format(dateOut);

        return strDate;
    }

    public static Date dateFormatterFromStringToDate(String date) {


        SimpleDateFormat inputDate = new SimpleDateFormat(ConstantsValues.DATE_PATTERN);
        Date outDate = null;
        try {
            outDate = inputDate.parse(date);
        } catch (ParseException e) {
            //TODO hiding exceptions is a bad practise
            e.printStackTrace();
        }
        return outDate;
    }



    /**
     * метод парсит из long в тип Date
     *
     * @param date число в long
     * @return Date объект
     */
    public static Date dateFormatterFromLongToDate(long date) {
        return new Date(date);
    }

    public static long dateFormatterFromDateToLong(Date date) {
        long time = date.getTime();
        return time;
    }


    /**
     * метод парсит из Date в строку
     *
     * @param date
     * @return строка даты
     */
    public static String dateFormatterFromDateToString(Date date) {
        String strDate = "";
        SimpleDateFormat inputDate = new SimpleDateFormat(ConstantsValues.DATE_PATTERN);
        strDate = inputDate.format(date);
        return strDate;
    }
}
