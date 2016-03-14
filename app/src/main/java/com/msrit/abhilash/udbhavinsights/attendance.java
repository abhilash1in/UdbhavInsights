package com.msrit.abhilash.udbhavinsights;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Abhilash on 05/02/2016.
 */
public class attendance extends Fragment{

    TextView percentage,attended,held;
    public attendance() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_attendance, container, false);

        /*final ProgressDialog progress;
        progress = ProgressDialog.show(getActivity(), "Please wait",
                "Loading", true);*/

         percentage = (TextView) root.findViewById(R.id.percent);
         attended = (TextView) root.findViewById(R.id.attendedNum);
         held = (TextView) root.findViewById(R.id.heldNum);
        held.setText(UdbhavInsights.h + "");
        attended.setText(UdbhavInsights.a + "");
        if(UdbhavInsights.h!=0)
        {
            percentage.setText((UdbhavInsights.a * 100 / UdbhavInsights.h) + "%");
        }

        /*ParseQuery<ParseObject> bquery = ParseQuery.getQuery("attendance");
        bquery.whereEqualTo("user", ParseUser.getCurrentUser());
        Log.v("test", "login.currentuser called");
        bquery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                progress.dismiss();
                if (e == null) {
                    if (list.size() > 0) {
                        Log.v("test", list.size() + "");
                        ParseObject p = list.get(0);
                        int a = 0, h = 0;
                        a= p.getInt("attended");
                        //todo find meets held
                        h = 1;
                        held.setText(h + "");
                        attended.setText(a + "");
                        percentage.setText((a * 100 / h) + "%");
                    } else {
                        Log.v("test", "attendance list size is 0");
                    }

                } else {
                    e.printStackTrace();
                }


            }
        });*/

        return root;
    }
}
