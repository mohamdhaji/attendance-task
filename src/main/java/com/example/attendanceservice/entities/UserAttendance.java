package com.example.attendanceservice.entities;

public class UserAttendance {

    private String userName;
    private String report;

    public UserAttendance(String userName, String report) {
        this.userName = userName;
        this.report = report;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
