package com.doomole.stockproject.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Util {
    public static HashMap<String, Object> getStringToHashMap(String data) {
        try {
            ObjectMapper mapperObj = new ObjectMapper();
            HashMap<String, Object> resultMap = mapperObj.readValue(data,
                    new TypeReference<HashMap<String, Object>>(){});
            return resultMap;
        } catch (Exception e) {
            return null;
        }
    }

    /** date String 리턴 */
    public static String newDateString() {
        return dateToString(new Date());
    }

    /** date to String */
    public static String dateToString(Date dateTime) {
        return Util.dateToString(dateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /** date to String */
    public static String dateToString(Date dateTime, String format) {
        if(dateTime != null) {
            SimpleDateFormat transFormat = new SimpleDateFormat(format);
            return transFormat.format(dateTime);
        }

        return null;
    }
}
