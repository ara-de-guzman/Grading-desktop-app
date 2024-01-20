package com.example.myjavaproject;

import java.sql.Date;
import java.sql.Timestamp;

public class AttendanceQuery {


    private int stdnt_id;

    private Date date;

    public AttendanceQuery(int stdnt_id, Date date) {
        this.stdnt_id = stdnt_id;
        this.date = date;

    }

    public int getStdnt_id() {
        return stdnt_id;
    }

    public Date getDate() {
        return date;
    }
}
