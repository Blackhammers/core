package com.blackhammers.kalisz.planuz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kalis on 2018-04-24.
 */

public class Courses implements Parcelable{
    int id;
    String name, url;

    public Courses() {
    }

    public Courses(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    protected Courses(Parcel in) {
        id = in.readInt();
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Courses> CREATOR = new Creator<Courses>() {
        @Override
        public Courses createFromParcel(Parcel in) {
            return new Courses(in);
        }

        @Override
        public Courses[] newArray(int size) {
            return new Courses[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(url);
    }
}
