package utils;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cesar on 10/1/2017.
 */

public class DateFormatter {

    /**
     * Method to format a date as a string into a Date object
     * @param dateAsString
     * @return
     */
    public static Date createDateFromString(String dateAsString) {
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = iso8601Format.parse(dateAsString);
            return date;
        } catch (ParseException e) {
            Log.e("formatStringDateTime", "Parsing ISO8601 datetime failed", e);
            return null;
        }
    }

    /**
     * Method to return a date as a string
     * @param date
     * @return
     */
    public static String createStringFromDate(Date date) {
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return iso8601Format.format(date);
    }
}
