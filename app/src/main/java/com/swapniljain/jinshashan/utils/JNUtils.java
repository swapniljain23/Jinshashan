package com.swapniljain.jinshashan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JNUtils {

    public static Calendar parseDate(String dateString) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(dateString));
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int calculateAge(Calendar birthDate) {
        if (birthDate != null) {
            Calendar today = Calendar.getInstance();
            int dobYear = birthDate.get(Calendar.YEAR);
            int currYear = today.get(Calendar.YEAR);
            int age = currYear - dobYear;

            int dobMonth = birthDate.get(Calendar.MONTH);
            int currMonth = birthDate.get(Calendar.MONTH);
            if (dobMonth > currMonth) {
                age--;
            } else if (dobMonth == currMonth){
                int dobDay = birthDate.get(Calendar.DAY_OF_MONTH);
                int currDay = birthDate.get(Calendar.DAY_OF_MONTH);
                if(dobDay > currDay) {
                    age--;
                }
            }
            return age;
        } else {
            return 0;
        }
    }

    public static int calculateAge(String dateString) {
        return calculateAge(parseDate(dateString));
    }
}
