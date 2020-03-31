package attendance.application.user.repositories;

import attendance.application.user.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Integer>{



        Attendance findByUser_IdAndDate(int userId, String date);

        Attendance findByUserId(int userId);

        List<Attendance> findAllByDate(String date);

        List<Attendance> findAllByDateBetween(
                Date firstMonthDay,
                Date lastMonthDay);



}
