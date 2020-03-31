package attendance.application.admin.controller;


import attendance.application.user.entity.Attendance;
import attendance.application.user.repositories.AttendanceRepo;
import attendance.application.user.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

//    @GetMapping("/permonth")
//    public List<UsersAttendance> getReportsPerMonth(@RequestParam(value = "month", defaultValue = "") int month){
//
//
//    }


}
