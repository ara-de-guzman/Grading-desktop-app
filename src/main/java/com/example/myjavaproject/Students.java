package com.example.myjavaproject;

import java.sql.Date;

public class Students {


    private int id;

    private String fname;

    private String lname;
    private int age;
    private String address;

    private String gender;

    private Double math1;
    private Double math2;

    private Double sci1;
    private Double sci2;

    private Double eng1;
    private Double eng2;

    private Double ss1;
    private Double ss2;

    private Double ar1;
    private Double ar2;

    private Double hc1;
    private Double hc2;

    private Double finalGrade;

    private String letterGrade;

    private Date dob;

    private String passKey;




    public Students() {
    }

    public Students(int id, String fname, String lname, int age, String address,String gender,Date dob, Double math1, Double math2, Double sci1, Double sci2, Double eng1, Double eng2, Double ss1, Double ss2, Double ar1, Double ar2, Double hc1, Double hc2, Double finalGrade,String letterGrade,String passKey) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.math1 = math1;
        this.math2 = math2;
        this.sci1 = sci1;
        this.sci2 = sci2;
        this.eng1 = eng1;
        this.eng2 = eng2;
        this.ss1 = ss1;
        this.ss2 = ss2;
        this.ar1 = ar1;
        this.ar2 = ar2;
        this.hc1 = hc1;
        this.hc2 = hc2;
        this.finalGrade = finalGrade;
        this.letterGrade = letterGrade;
        this.passKey = passKey;

    }



    public int getId() {
        return id;
    }

    public String getFname() {

        return fname;
    }

    public String getLname() {

        return lname;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getGender(){
        return gender;
    }
    public Date getDob(){
        return dob;
    }

    public Double getMath1() {
        return math1;
    }

    public Double getMath2() {
        return math2;
    }

    public Double getSci1() {
        return sci1;
    }

    public Double getSci2() {
        return sci2;
    }

    public Double getEng1() {
        return eng1;
    }

    public Double getEng2() {
        return eng2;
    }

    public Double getSs1() {
        return ss1;
    }

    public Double getSs2() {
        return ss2;
    }

    public Double getAr1() {
        return ar1;
    }

    public Double getAr2() {
        return ar2;
    }

    public Double getHc1() {
        return hc1;
    }

    public Double getHc2() {

        return hc2;
    }
    public Double getFinalGrade() {

        return finalGrade;
    }
    public String getLetterGrade(){
        return letterGrade;
    }

    public String getPassKey(){
        return passKey;
    }
}
