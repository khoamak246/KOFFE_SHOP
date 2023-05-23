package com.koffe.koffe.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ConvertTimeStamp {
    public static Timestamp formatTimeStampFromString(String inputString) {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(inputString);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public static String handleInputDateTimeString(String date, String time) {
        String dateTimeAfterFormat;
        String dateAfterFormat = handleDateString(date);
        String timeAfterFormat = handleTimeString(time);
        dateTimeAfterFormat = dateAfterFormat + " " + timeAfterFormat;
        return dateTimeAfterFormat;
    }

    private static String handleDateString(String date) {
        String dateAfterFormat;
        List<String> tempDateArr = Arrays.asList(date.split("/"));
        String tempDate = tempDateArr.get(1);
        tempDateArr.set(1, tempDateArr.get(0));
        tempDateArr.set(0, tempDate);
        Collections.reverse(tempDateArr);
        dateAfterFormat = String.join("-", tempDateArr);
        return dateAfterFormat;
    }

    private static String handleTimeString(String time) {
        String timeAfterFormat;
        if (time.contains(" AM")) {
            timeAfterFormat = time.replaceAll(" AM", ":00.000");
        } else {
            String tempTimeAfterFormat = time.replaceAll(" PM", "");
            String[] tempTime_AfterFormat_Arr = tempTimeAfterFormat.split(":");
            if (tempTime_AfterFormat_Arr[0].equals("12")) {
                tempTime_AfterFormat_Arr[0] = "0";
            } else {
                tempTime_AfterFormat_Arr[0] = String.valueOf(Integer.parseInt(tempTime_AfterFormat_Arr[0]) + 12);
            }
            timeAfterFormat = tempTime_AfterFormat_Arr[0] + ":" + tempTime_AfterFormat_Arr[1] + ":00.000";
        }
        return timeAfterFormat;
    }
}
