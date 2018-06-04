package com.blackhammers.kalisz.planuz;

/**
 * Created by kalis on 2018-05-15.
 */

public class Groups {
    int id;
    String name,url;

    public Groups() {
    }

    public Groups(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
