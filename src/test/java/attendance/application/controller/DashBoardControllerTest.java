package attendance.application.controller;

import attendance.application.user.controller.DashBoardController;
import attendance.application.user.entity.Attendance;
import attendance.application.user.entity.User;
import attendance.application.user.repositories.AttendanceRepo;
import attendance.application.user.repositories.UserRepo;
import attendance.application.user.service.DashboardService;
import attendance.application.user.service.TimeService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Tag("dashboard")
public class DashBoardControllerTest {


    @InjectMocks
    DashboardService dashboardService;

    @Mock
    UserRepo userRepo;

    User  user;


    @BeforeEach
    public void userInfoTest(){
        MockitoAnnotations.initMocks(this);
        user = new User();

        when(userRepo.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(user));
        Optional<User> savedUser=userRepo.findById(anyInt());
        verify(userRepo).findById(anyInt());
        assertNotNull(dashboardService.checkUser(anyInt()));

    }




    @Nested
    public class UserAttendance{

    @InjectMocks
    DashBoardController dashBoardController;

    @Mock
    AttendanceRepo attendanceRepo;

    @Mock
    DashboardService dashboardService;
    Attendance  attendace;



    @BeforeEach
    void initEach() {

        MockitoAnnotations.initMocks(this);
        attendace = new Attendance();

        when(dashboardService.checkUser(anyInt())).thenReturn(user);
        User u=dashboardService.checkUser(anyInt());

        when(attendanceRepo.findByUser_IdAndDate(anyInt(),anyObject())).thenReturn(java.util.Optional.ofNullable(null));

       

        when(attendanceRepo.save(any(Attendance.class))).thenReturn(attendace);
        Attendance savedAttendance=attendanceRepo.save(new Attendance());
        verify(attendanceRepo).save(any(Attendance.class));


//        verify(attendanceRepo).findByUser_IdAndDate(anyInt(),anyObject());


    }


    @DisplayName("check in")
    @Test
    void checkIn() {

        assertNotNull(dashBoardController.checkIn(anyInt()));
    }

    @DisplayName("check out")
    @Test
    void checkOut() {


        assertNotNull(dashBoardController.checkOut(anyInt()));
    }

    }




}