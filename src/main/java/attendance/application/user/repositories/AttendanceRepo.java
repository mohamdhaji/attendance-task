package attendance.application.user.repositories;

import attendance.application.user.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Integer>{



        Attendance findByUser_IdAndDate(int userId, Date date);

        Attendance findByUserId(int userId);

        List<Attendance> findAllByDate(Date date);


        @Query(
                value = "SELECT * FROM attendance a where a.date between :firstMonthDay AND :lastMonthDay",
                nativeQuery=true
        )
        List<Attendance> findByDateBetween(@Param("firstMonthDay")
                Date firstMonthDay,
                                           @Param("lastMonthDay") Date lastMonthDay);



}
