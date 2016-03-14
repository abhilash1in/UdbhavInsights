package com.msrit.abhilash.udbhavinsights.TakeAttendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.msrit.abhilash.udbhavinsights.Login;
import com.msrit.abhilash.udbhavinsights.MainActivity;
import com.msrit.abhilash.udbhavinsights.R;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Test extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView mRecyclerView;
    private MyAdapter2 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<itemData> myDataset;

    private List<ParseObject> r;
    String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        /*ParseAnalytics.trackAppOpenedInBackground(getIntent());*/
        /*try{
            Intent intent = getIntent();
            Bundle extras =intent.getExtras();
            if(extras!=null)
            {
                String jsonData = extras.getString("com.parse.Data");
                JSONObject json;
                json = new JSONObject(jsonData);
                String pushStore = json.getString("alert");
                Toast.makeText(Test.this, pushStore, Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/

        myDataset = new ArrayList<>();

        /*FetchData.fetchFromLocal(this);*/
        r = FetchData.result;
        Log.v("test",r.size()+"");

        //todo compute and pass number of meets held as the last parameter

        for(int i=0;i<r.size();i++)
        {
            ParseObject p = r.get(i);
            ParseObject temp = p.getParseObject("user");
            myDataset.add(new itemData(temp.getString("name"), temp.get("phone").toString(), p.getInt("attended"), 10));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        /*SharedPreferences.Editor editor = prefs.edit();
        editor.apply();
*/
        String j = prefs.getString("status",null);
        Log.v("date",prefs.getInt("editedOnDate",-1)+"");
        /*Log.v("test status",j);*/
        JSONObject st;
        if(j!=null)
        {
            Log.v("test status",j);
            try
            {
                st = new JSONObject(j);
                st.put("init",1);
                // specify an adapter (see also next example)
                mAdapter = new MyAdapter2(this, myDataset , st);
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v("test",e.getMessage());
                e.printStackTrace();
            }
        }

        // specify an adapter (see also next example)
        else {
            mAdapter = new MyAdapter2(this, myDataset);
        }
        mRecyclerView.setAdapter(mAdapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);


        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        mAdapter.setFilter(myDataset);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });

        return true;
    }


    @Override
    public boolean onQueryTextChange(String query) {

        final List<itemData> filteredModelList = filter(myDataset, query);
        mAdapter.setFilter(filteredModelList);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<itemData> filter(List<itemData> models, String query) {
        query = query.toLowerCase();

        final List<itemData> filteredModelList = new ArrayList<>();
        for (itemData model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

}
