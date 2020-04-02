package attendance.application.user.controller;


import attendance.application.exception.UserNotFoundException;
import attendance.application.user.entity.Attendance;
import attendance.application.user.entity.User;
import attendance.application.user.repositories.AttendanceRepo;
import attendance.application.user.repositories.UserRepo;
import attendance.application.user.service.DashboardService;
import attendance.application.user.service.TimeService;
import attendance.application.exception.UserCheckedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    AttendanceRepo attendanceRepo;

    @Autowired
    DashboardService dashboardService;


    @PostMapping("/checkin")
    public ResponseEntity checkIn(@RequestParam(value = "userid", defaultValue = "") int userid) {

        User user =dashboardService.checkUser(userid);

        Optional<Attendance> attendance = attendanceRepo.findByUser_IdAndDate(userid, TimeService.getCurrentDate());

        if (attendance.isPresent()) {
            throw new UserCheckedException("Check-In Already Done Today!");
        }

        String checkInTime = TimeService.getCurrentTime();

        Attendance userAttendance = new Attendance(user, checkInTime,TimeService.getCurrentDate());
       Attendance a= attendanceRepo.save(userAttendance);

        return new ResponseEntity("Check-In Successfully", HttpStatus.OK);
    }


    @PostMapping("/checkout")
    public ResponseEntity checkOut(@RequestParam(value = "userid", defaultValue = "") int userid) {

        User user=dashboardService.checkUser(userid);

        Optional<Attendance> attendance = attendanceRepo.findByUser_IdAndDate(userid, TimeService.getCurrentDate());

        if (attendance.isPresent()) {

            if (attendance.get().getCheckOut() == null) {

                String checkOutTime = TimeService.getCurrentTime();

                attendance.get().setCheckOut(checkOutTime);

                Attendance a = attendanceRepo.save(attendance.get());

                return new ResponseEntity("Check-Out Successfully", HttpStatus.OK);

            } else {
                throw new UserCheckedException("Check-Out Already Done Today!");
            }

        } else {
            String checkOutTime = TimeService.getCurrentTime();

            Attendance userAttendance = new Attendance(checkOutTime, user, TimeService.getCurrentDate());
            return new ResponseEntity("Check-Out Successfully", HttpStatus.OK);

        }

    }

    @PostMapping("/report")
    public ResponseEntity addReport(@RequestParam(value = "report", defaultValue = "") String report, @RequestParam(value = "userid", defaultValue = "")int userid) {

        User user = dashboardService.checkUser(userid);

        Attendance attendance = attendanceRepo.findByUserId(user.getId());
        attendance.setReport(report);
        attendance.setUser(user);

        attendanceRepo.save(attendance);

        return new ResponseEntity("Report Added Successfully", HttpStatus.OK);

    }

}
