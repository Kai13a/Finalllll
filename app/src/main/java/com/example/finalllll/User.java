package com.example.finalllll;


public class User {

    public String firstname,middlename,lastname,srcode, age, email,dob,poo,phonenum,gender,civil,role,campus;


    public User(){

    }

    public User (String firstname, String middlename, String lastname, String srcode, String age, String email,
                 String dob, String poo, String phonenum, String gender, String civil, String role, String campus){
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.srcode = srcode;
        this.age = age;
        this.email = email;
        this.dob = dob;
        this.poo = poo;
        this.phonenum = phonenum;
        this.gender = gender;
        this.civil = civil;
        this.role = role;
        this.campus = campus;


    }

    public String firstname() {
        return firstname;
    }
}
