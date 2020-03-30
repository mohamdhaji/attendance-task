package com.example.attendanceservice.controllers;


import com.example.attendanceservice.entities.Attendance;
import com.example.attendanceservice.entities.User;
import com.example.attendanceservice.exception.UserCheckedException;
import com.example.attendanceservice.repositories.AttendanceRepo;
import com.example.attendanceservice.services.TimeService;
import com.example.attendanceservice.session.LoginSession;
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



    @PostMapping("/checkin")
    public ResponseEntity checkIn() {

        User user;
        try {
            user = (User) LoginSession.session.getAttribute("LOGIN-SESSION");
        } catch (Exception e) {

            return new ResponseEntity("Session Closed", HttpStatus.UNAUTHORIZED);

        }

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
    public ResponseEntity checkOut() {

        User user;
        try {
            user = (User) LoginSession.session.getAttribute("LOGIN-SESSION");
        } catch (Exception e) {

            return new ResponseEntity("Session Closed", HttpStatus.UNAUTHORIZED);

        }
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
    public ResponseEntity addReport(@RequestParam(value = "report", defaultValue = "") String report) {

        User user;
        try {
            user = (User) LoginSession.session.getAttribute("LOGIN-SESSION");
        } catch (Exception e) {

            return new ResponseEntity("Session Closed", HttpStatus.UNAUTHORIZED);

        }

        Optional<Attendance> attendance = Optional.ofNullable(attendanceRepo.findByUser_IdAndDate(user.getId(), java.time.LocalDate.now() + ""));

        if (attendance.isPresent()) {
            if (attendance.get().getCheckOut() != null) {

                attendance.get().setReport(report);
                attendanceRepo.save(attendance.get());

                return new ResponseEntity("Report Added Successfully", HttpStatus.OK);

            }


        }
        return new ResponseEntity("Check-Out First!", HttpStatus.OK);


    }



}
