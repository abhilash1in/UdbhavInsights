package com.msrit.abhilash.udbhavinsights.TakeAttendance;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Abhilash on 05/02/2016.
 */
public class FetchData {

    /*public static List<ParseUser> p =new ArrayList<>();
    public static List<String> names = new ArrayList<>();
*/

    public static List<ParseObject> result = new ArrayList<>();


    /*public static List<ParseObject> getUsers()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("attendance");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.v("test","fetched");
                    result=objects;

                *//*
                    Log.v("test",objects.size()+"");
                    for(int i=0;i<objects.size();i++)
                    {
                        *//**//*ParseUser pu = objects.get(i).getParseUser("user");*//**//*
                        try {
                            p.add(objects.get(i).fetchIfNeeded().getParseUser("user"));
                        }
                        catch (Exception e1)
                        {
                            e1.printStackTrace();
                        }

                    }

                    try {

                        for(int i=0;i<p.size();i++)
                        {
                            names.add(p.get(i).fetchIfNeeded().getString("name"));
                            Log.v("test",p.get(i).fetchIfNeeded().getString("name"));
                        }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }*//*


                } else {
                    //failed
                }
            }
        });

        return null;
    }*/

    public static void display(Context context)
    {
        Log.v("test",result.size()+"");
        for(int i=0;i<result.size();i++)
        {
            try {
                ParseObject p = result.get(i).getParseObject("user");
                Log.v("test", p.getString("name"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(context, "diaplay: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }



    public static void saveToLocal(final Context context)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("attendance");
        query.include("user");
        query.orderByAscending("nameSort");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.v("test", "fetched");
                    result = objects;
                    ParseObject.pinAllInBackground(objects);
                    Toast.makeText(context, "Fetched data successfully!", Toast.LENGTH_SHORT).show();

                } else {
                    //failed
                }
            }
        });

    }

    public static void fetchFromLocal(final Context context)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("attendance");
        query.include("user");
        query.fromLocalDatastore();
        query.orderByAscending("nameSort");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null)
                {
                    if(list.size()>0)
                    {
                        /*Collections.sort(list, new Comparator<ParseObject>() {
                            @Override
                            public int compare(ParseObject lhs, ParseObject rhs) {
                                return indexOfDifference(lhs.getParseObject("user").getString("name").toLowerCase(),rhs.getParseObject("user").getString("name").toLowerCase());
                            }

                            public int indexOfDifference(String str1, String str2) {
                                if (str1 == str2) {
                                    return -1;
                                }
                                if (str1 == null || str2 == null) {
                                    return 0;
                                }
                                int i;
                                for (i = 0; i < str1.length() && i < str2.length(); ++i) {
                                    if (str1.charAt(i) != str2.charAt(i)) {
                                        break;
                                    }
                                }
                                if (i < str2.length() || i < str1.length()) {
                                    return i;
                                }
                                return -1;
                            }
                        });*/
                        result=list;
                    }
                    else
                    {
                        Toast.makeText(context, "Connect to the internet and fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void update(final Context context)
    {
        ParseObject.saveAllInBackground(result, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
