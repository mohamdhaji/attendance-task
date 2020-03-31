package attendance.application.user.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeService {

    public static String getCurrentTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh:mm a");
        String checkInTime = myDateObj.format(myFormatObj);

        return checkInTime;
    }
}
