package com.msrit.abhilash.udbhavinsights;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.msrit.abhilash.udbhavinsights.TakeAttendance.FetchData;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Abhilash on 05/02/2016.
 */
public class UdbhavInsights extends android.app.Application {
    public static int a,h;
    @Override
    public void onCreate() {
        super.onCreate();

        //This will only be called once in your app's entire lifecycle.
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, "m3KC4rEr5BIXw0UjFvYhra91AOgXMxLI32mHAYtS\n", "ZrTRK7DzugxW3N2J1U63HqX3NNR3xgqHFz4DMcd2");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        /*final ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/

        /*{
            try
            {
                JSONObject test = new JSONObject();
                test.put("test1", "test1");
                test.put("test2", "test2");

                JSONObject test2 = new JSONObject();
                test2.put("test3", "test3");
                test2.put("test4", "test4");

                JSONObject test3 = new JSONObject();
                test3.put("test5", "test5");
                test3.put("test6", "test6");

                JSONObject test4 = new JSONObject();
                test4.put("test7", "test7");
                test4.put("test8", "test8");

                testObject.add("array", test.toString());
                testObject.add("array", test2.toString());

                Log.v("test","json object created");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }*/

        ParseQuery<ParseObject> bquery = ParseQuery.getQuery("attendance");
        bquery.whereEqualTo("user", ParseUser.getCurrentUser());
        Log.v("test", "login.currentuser called");
        bquery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null) {
                    if (list.size() > 0) {
                        Log.v("test", list.size() + "");
                        ParseObject p = list.get(0);
                        a = 0;
                        a = p.getInt("attended");
                        //todo find meets held
                        h = 1;
                    } else {
                        Log.v("test", "attendance list size is 0");
                    }

                } else {
                    e.printStackTrace();
                }


            }
        });


        //TODO move to application class of new App designed to take attendance
        //fetches users details which are saved locally
        FetchData.fetchFromLocal(this);
        SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int e = prefs.getInt("editedOnDate", -1);
        final Calendar c = Calendar.getInstance();
        if(e>-1&&e!=c.get(Calendar.DAY_OF_MONTH))
        {
            prefs.edit().clear().commit();
        }
    }
}
