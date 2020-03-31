package attendance.application.user.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public static java.sql.Date getFirstDayOfTheMonth(int month){

        LocalDate todaydate = LocalDate.now().withMonth(month);

        todaydate= todaydate.withDayOfMonth(1);


        return java.sql.Date.valueOf(todaydate);



    }

    public static java.sql.Date getLastDayOfTheMonth(int month){

        LocalDate todaydate = LocalDate.now().withMonth(month);

        GregorianCalendar calendar = new GregorianCalendar();

        // set the date of the calendar to the date provided
        calendar.set( Calendar.getInstance().get(Calendar.YEAR), month-1, 1);

        int dayInt = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);


        todaydate=todaydate.withDayOfMonth(dayInt);


        return java.sql.Date.valueOf(todaydate);


    }



}
