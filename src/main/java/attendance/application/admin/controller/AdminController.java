package attendance.application.admin.controller;


import attendance.application.admin.service.QueryService;
import attendance.application.admin.entity.UserAttendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/reports")
public class AdminController {



    @Autowired
    QueryService queryService;

    @GetMapping("/perday")
    public List<UserAttendance> getReportsPerDay(){

        return  queryService.JPQLQuery();
    }

    @GetMapping("/permonth")
    public List<UserAttendance> getReportsPerMonth(@RequestParam(value = "month", defaultValue = "") int month){

        return  queryService.JPQLQueryTwo(month);
    }


}
