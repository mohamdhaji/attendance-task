package com.example.attendanceservice.repositories;

import com.example.attendanceservice.entities.Attendance;
import com.example.attendanceservice.entities.User;
import com.example.attendanceservice.entities.UserAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Integer>{



//    Attendance findByUserAndDate(User user, String date);

//        public static final String TODAY_REPORTS = "SELECT report FROM attendance a where a.date=?#{[0]}";

//        @Query(
//                value = TODAY_REPORTS,
//                nativeQuery = true)
//        List<String> findReportsByDate(String date);

        Attendance findByUser_IdAndDate(int userId, String date);





}
