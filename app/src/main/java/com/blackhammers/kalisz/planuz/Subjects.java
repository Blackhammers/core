package com.blackhammers.kalisz.planuz;

import java.io.Serializable;

/**
 * Created by kalis on 2018-05-25.
 */

public class Subjects implements Serializable {
    String name;

    public Subjects() {
    }

    public Subjects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
