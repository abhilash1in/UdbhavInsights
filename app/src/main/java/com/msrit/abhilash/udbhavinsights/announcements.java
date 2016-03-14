package com.msrit.abhilash.udbhavinsights;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Abhilash on 05/02/2016.
 */
public class announcements extends Fragment {

    private static int local_size;
    private static String sDate;
    private static RecyclerView mRecyclerView;
    private static notificationsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static SharedPreferences prefs;
    private static java.util.Date subdate = new Date();
    private static SwipeRefreshLayout mSwipeRefreshLayout;

    public announcements() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_announcements, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);


        mRecyclerView = (RecyclerView) root.findViewById(R.id.notifs_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        prefs = getActivity().getSharedPreferences("notifs", Context.MODE_PRIVATE);
        fetch(getContext());

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetch(getContext());
            }
        });


        return root;
    }

    public static void fetch(Context context) {
        if (isNetworkAvailable(context)) {

            sDate = prefs.getString("last_fetched", null);
            local_size = prefs.getInt("size", 0);

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Notifications");
            if (sDate != null) {
                try {
                    query.whereGreaterThan("updatedAt", new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(sDate));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            query.orderByDescending("updatedAt");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (list != null) {
                        Log.v("test fetch", list.size() + "");
                    }
                    if (e == null) {
                        if (list.size() > 0) {
                            subdate = list.get(0).getUpdatedAt();
                            Log.v("test", "date before addition: " + subdate.toString());
                            subdate.setTime(subdate.getTime() + 30000);
                            Log.v("test", "date after addition: " + subdate.toString());
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("last_fetched", subdate.toString());
                            editor.putInt("size", local_size + list.size());
                            editor.apply();
                        }

                        for (int i = local_size, j = 0; i < local_size + list.size(); i++, j++) {
                            Log.v("test", "saved in sharedprefs");
                            Log.v("test prefs saved", j + "");
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("notifTitle" + i, list.get(j).getString("title"));
                            editor.putString("notifContent" + i, list.get(j).getString("content"));
                            editor.apply();
                            Log.v("ivalue", i + "");
                        }

                        List<notificationItem> myDataset = new ArrayList<>();
                        for (int i = 0; i < prefs.getInt("size", 0); i++) {
                            myDataset.add(new notificationItem(prefs.getString("notifTitle" + i, "not found"), prefs.getString("notifContent" + i, "not found")));
                        }
                        mAdapter = new notificationsAdapter(myDataset);
                        mRecyclerView.setAdapter(mAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                }
            });
        }
        else
        {
            Toast.makeText(context,"No internet connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();
    }
}
