package attendance.application.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "attendance")
public class Attendance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    private String checkIn;
    private String checkOut;

    @Temporal(TemporalType.DATE)
    private LocalDateTime date;
    private String report;

    public Attendance() {
    }

    public Attendance(User user, String checkIn, LocalDateTime date) {
        this.user = user;
        this.checkIn = checkIn;
        this.date = date;
    }

    public Attendance( String checkOut,User user,LocalDateTime date) {
        this.user = user;
        this.checkOut = checkOut;
        this.date = date;
    }




    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
