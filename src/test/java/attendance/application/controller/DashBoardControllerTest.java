package attendance.application.controller;

import attendance.application.user.controller.DashBoardController;
import attendance.application.user.entity.Attendance;
import attendance.application.user.entity.User;
import attendance.application.user.repositories.AttendanceRepo;
import attendance.application.user.repositories.UserRepo;
import attendance.application.user.service.TimeService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class DashBoardControllerTest {

    @InjectMocks
    DashBoardController dashBoardController;

    @Mock
    AttendanceRepo attendanceRepo;

    @Mock
    UserRepo userRepo;



    static TestInfo testInfo;

    User user;
    Attendance attendace;





    @BeforeEach
    void initEach(TestInfo testInfo) {

        MockitoAnnotations.initMocks(this);
        this.testInfo = testInfo;
        user=new User();
        user.setUserName("klaus");
        user.setPassword("123");
        attendace=new Attendance();
        attendace.setId(1);
        attendace.setCheckIn("2020");
        attendace.setCheckOut("2020");
        attendace.setUser(user);
        attendace.setReport("hi");
        attendace.setDate(TimeService.getCurrentDate());

    }


    @DisplayName("check in")
    @Tag("post")
    @Test
    void checkIn() {
        when(userRepo.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(user));

        when(attendanceRepo.findByUser_IdAndDate(user.getId(),TimeService.getCurrentDate())).thenReturn(java.util.Optional.ofNullable(attendace));

        assertNotNull(dashBoardController.checkIn(anyInt()));
    }

    @DisplayName("check out")
    @Tag("post")
    @Test
    void checkOut() {
        when(userRepo.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(user));

        when(attendanceRepo.findByUser_IdAndDate(user.getId(),TimeService.getCurrentDate())).thenReturn(java.util.Optional.ofNullable(attendace));

        assertNotNull(dashBoardController.checkOut(anyInt()));
    }



}