package com.example.abc.firetest;

/**
 * Created by 'abc on 3/4/2018.
 */

public class Information {

    public int id , mohsens , image;
    public String name;
    public String UID ;

    public Information(int id, int mohsens, int image, String name) {
        this.id = id;
        this.mohsens = mohsens;
        this.image = image;
        this.name = name;
    }
    public Information(int id, int mohsens, int image, String name , String UID)
    {
        this.id = id;
        this.mohsens = mohsens;
        this.image = image;
        this.name = name;
        this.UID = UID;
    }
}
