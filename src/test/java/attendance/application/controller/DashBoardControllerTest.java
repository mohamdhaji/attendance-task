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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@Tag("dashboard")
public class DashBoardControllerTest {

    @InjectMocks
    DashBoardController dashBoardController;

    @Mock
    AttendanceRepo attendanceRepo;

    @Mock
    DashboardService dashboardService;
    User  user;
    Attendance  attendace;

    @BeforeEach
    void initEach() {

        MockitoAnnotations.initMocks(this);
        user = new User();
          attendace = new Attendance();
    }


    @DisplayName("check in")
    @Test
    void checkIn() {

        when(dashboardService.checkUser(anyInt())).thenReturn(user);

        when(attendanceRepo.findByUser_IdAndDate(user.getId(), TimeService.getCurrentDate())).thenReturn(java.util.Optional.ofNullable(attendace));

        when(attendanceRepo.save(any(Attendance.class))).thenReturn(attendace);
        assertNotNull(dashBoardController.checkIn(anyInt()));
    }

    @DisplayName("check out")
    @Test
    void checkOut() {
        when(dashboardService.checkUser(anyInt())).thenReturn(user);

        when(attendanceRepo.findByUser_IdAndDate(user.getId(), TimeService.getCurrentDate())).thenReturn(java.util.Optional.ofNullable(attendace));
        when(attendanceRepo.save(any(Attendance.class))).thenReturn(attendace);

        assertNotNull(dashBoardController.checkOut(anyInt()));
    }

}