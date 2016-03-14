package com.msrit.abhilash.udbhavinsights.TakeAttendance;

import android.util.Log;

import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Abhilash on 06/02/2016.
 */
public class itemData {

    String name;
    String phone;
    int held,attended;

    public itemData(String name, String phone, int held, int attended) {
        this.name = name;
        this.phone = phone;
        this.held = held;
        this.attended = attended;
        /*Log.v("test",values.toString());*/
    }


    public String getName() {
        return name;
    }


    public String getPhone() {
        return phone;
    }

    public int getHeld() {
        return held;
    }

    public int getAttended() {
        return attended;
    }

}
