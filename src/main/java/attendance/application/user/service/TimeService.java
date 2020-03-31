package attendance.application.user.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeService {

    public static String getCurrentTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh:mm a");
        String checkInTime = myDateObj.format(myFormatObj);

        return checkInTime;
    }

    public static Date getCurrentDate(){
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        return date;
    }
}
