package com.msrit.abhilash.udbhavinsights;

import android.content.Context;
import android.content.SharedPreferences;

import com.parse.Parse;

import com.parse.ParseInstallation;

import java.util.Calendar;

/**
 * Created by Abhilash on 05/02/2016.
 */
public class UdbhavInsights extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //This will only be called once in your app's entire lifecycle.
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, "m3KC4rEr5BIXw0UjFvYhra91AOgXMxLI32mHAYtS\n", "ZrTRK7DzugxW3N2J1U63HqX3NNR3xgqHFz4DMcd2");
        ParseInstallation.getCurrentInstallation().saveInBackground();


        //TODO move to application class of new App designed to take attendance
        //fetches users details which are saved locally
        SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int e = prefs.getInt("editedOnDate", -1);
        final Calendar c = Calendar.getInstance();
        if(e>-1&&e!=c.get(Calendar.DAY_OF_MONTH))
        {
            prefs.edit().clear().apply();
        }
    }
}
