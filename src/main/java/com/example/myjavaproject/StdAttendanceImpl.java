package com.example.myjavaproject;

import java.sql.Time;
import java.util.Date;

public class StdAttendanceImpl extends Students {


    private int stdid;

    private String fname;

    private String lname;

    public Date date;

    public String present;



    public StdAttendanceImpl(int stdid, String fname, String lname, Date date, String present) {
        this.stdid = stdid;
        this.fname = fname;
        this.lname = lname;
        this.date = date;
        this.present = present;

    }

    public int getStdid() {
        return stdid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDate() {
        return date;
    }

    public String getPresent(){
        return present;
}
}
