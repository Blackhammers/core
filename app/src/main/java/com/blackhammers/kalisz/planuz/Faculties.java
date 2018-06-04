package com.blackhammers.kalisz.planuz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Map;

/**
 * Created by kalis on 2018-04-09.
 */

public class Faculties {
    String name, address;
    int id;
//    public Map<String,Courses> courses;

    public Faculties(String name, String address,int id, Map<String, Courses> courses) {
        this.name = name;
        this.address = address;
        this.id = id;
       // this.courses = courses;
    }

    public Faculties() {
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getaddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //    public Map<String, Courses> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Map<String, Courses> courses) {
//        this.courses = courses;
//    }
}
