package attendance.application.admin.controller;


import attendance.application.user.entity.Attendance;
import attendance.application.user.repositories.AttendanceRepo;
import attendance.application.user.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/reports")
public class AdminController {


    @Autowired
    AttendanceRepo attendanceRepo;

    @GetMapping("/perday")
    public List<Attendance> getReportsPerDay(){

        return attendanceRepo.findAllByDate(TimeService.getCurrentDate());
    }

    @GetMapping("/permonth")
    public List<Attendance> getReportsPerMonth(@RequestParam(value = "month") Integer month) throws ParseException {

        return attendanceRepo.findByDateBetween(TimeService.getFirstDayOfTheMonth(month),TimeService.getLastDayOfTheMonth(month));


    }


}
