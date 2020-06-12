package com.example.attendanceassistant;

public class DataClass_Attendance_View {


    private String p1,p2,p3,p4,evening,date,day;
    DataClass_Attendance_View(String p1,String p2,String p3,String p4,String evening,String date,String day)
{
    this.p1=p1;
    this.p2=p2;
    this.p3=p3;
    this.p4=p4;
    this.evening=evening;
    this.date=date;
    this.day=day;
}

    public String getDate()
    {
        return date;
    }

    public String getEvening() {
        return evening;
    }

    public String getDay() {
        return day;
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public String getP3() {
        return p3;
    }

    public String getP4()
    {
        return p4;
    }
}
