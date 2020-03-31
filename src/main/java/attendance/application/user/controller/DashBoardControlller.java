package attendance.application.user.controller;


import attendance.application.exception.UserNotFoundException;
import attendance.application.user.entity.Attendance;
import attendance.application.user.entity.User;
import attendance.application.user.repositories.AttendanceRepo;
import attendance.application.user.repositories.UserRepo;
import attendance.application.user.service.TimeService;
import attendance.application.exception.UserCheckedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class DashBoardControlller {

    @Autowired
    AttendanceRepo attendanceRepo;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/checkin")
    public ResponseEntity checkIn(@RequestBody User user) {


        Optional<Attendance> attendance = Optional.ofNullable(attendanceRepo.findByUser_IdAndDate(user.getId(), java.time.LocalDate.now() + ""));

        if (attendance.isPresent()) {
            throw new UserCheckedException("Check-In Already Today!");
        }

        String checkInTime = TimeService.getCurrentTime();


        Attendance userAttendance = new Attendance(user, checkInTime, java.time.LocalDate.now() + "");
        attendanceRepo.save(userAttendance);


        return new ResponseEntity("Check-In Successfully", HttpStatus.OK);
    }


    @PostMapping("/checkout")
    public ResponseEntity checkOut(@RequestBody User user) {


        Optional<Attendance> attendance = Optional.ofNullable(attendanceRepo.findByUser_IdAndDate(user.getId(), java.time.LocalDate.now() + ""));

        if (attendance.isPresent()) {

            if (attendance.get().getCheckOut() == null) {

                String checkOutTime = TimeService.getCurrentTime();

                attendance.get().setCheckOut(checkOutTime);

                attendanceRepo.save(attendance.get());

                return new ResponseEntity("Check-Out Successfully", HttpStatus.OK);

            } else {
                throw new UserCheckedException("Check-Out Already Today!");
            }

        } else {
            String checkOutTime = TimeService.getCurrentTime();

            Attendance userAttendance = new Attendance(checkOutTime, user, java.time.LocalDate.now() + "");
            return new ResponseEntity("Check-Out Successfully", HttpStatus.OK);

        }


    }


    @PostMapping("/report")
    public ResponseEntity addReport(@RequestParam(value = "report", defaultValue = "") String report, @RequestBody User u) {

        Optional<User> user = Optional.ofNullable(userRepo.findByUserNameAndPassword(u.getUserName(), u.getPassword()));

        if (!user.isPresent())
            throw new UserNotFoundException("check your name or password");

        Attendance attendance = new Attendance();
        attendance.setReport(report);
        attendance.setUser(user.get());


        return new ResponseEntity("Report Added Successfully", HttpStatus.OK);


    }


}
