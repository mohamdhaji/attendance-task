package attendance.application.user.repositories;

import attendance.application.user.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {


    Optional<Attendance> findByUser_IdAndDate(int userId, Date date);

    Attendance findByUserId(int userId);

    List<Attendance> findAllByDate(Date date);


//        @Query(
//                value = "SELECT * FROM attendance a where a.date >= :firstMonthDay AND a.date <= :lastMonthDay",
//                nativeQuery=true
//        )

    //                @Query(
//                value = "SELECT * FROM attendance a where a.date between :firstMonthDay AND :lastMonthDay",
//                nativeQuery=true
//        )
   // this is function or what ?
    // ?? its jpa function which has keywords such as find By Date is the attribute name Between is keyword ok
    List<Attendance> findByDateBetween(
            java.sql.Date firstMonthDay,
            java.sql.Date lastMonthDay);


}
