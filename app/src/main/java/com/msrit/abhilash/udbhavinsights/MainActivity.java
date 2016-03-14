package com.msrit.abhilash.udbhavinsights;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.msrit.abhilash.udbhavinsights.TakeAttendance.FetchData;
import com.msrit.abhilash.udbhavinsights.TakeAttendance.Test;
import com.parse.FunctionCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

public class MainActivity extends AppCompatActivity {

    static final int NUM_ITEMS = 2;
    private Toolbar toolbar;
    ViewPager mPager;
    private TabLayout tabLayout;
    SlidePagerAdapter mPagerAdapter;
    public String hint,uriString;
    ParseUser user;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);*/
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }*/

        user=ParseUser.getCurrentUser();
        if(user==null)
        {
            goToLogin();
        }


        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);



        try{
            Intent intent = getIntent();
            if(intent!=null){
                Log.v("test","intent received");
            }
            else
            {
                Log.v("test","intent NOT received");
            }
            Bundle extras =intent.getExtras();

            /*JSONObject cls = new JSONObject(intent.getStringExtra("com.parse.Data"));*/


            if(extras!=null)
            {
                Log.v("test", extras.toString());
                /*mPager.setCurrentItem(1);*/
                String jsonData = extras.getString("com.parse.Data");
                JSONObject json;
                json = new JSONObject(jsonData);
                uriString = json.optString("uri", (String)null);
                if(uriString!=null && !uriString.equals(""))
                {

                    if(uriString.startsWith("http"))
                    {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(uriString));
                        startActivity(i);
                    }
                    else if(uriString.startsWith("com.msrit.abhilash"))
                    {
                        Class cls = Class.forName(uriString);
                        Intent i = new Intent(this, cls);
                        startActivity(i);
                    }
                    else if (uriString.startsWith("com"))
                    {
                        Intent i = this.getPackageManager().getLaunchIntentForPackage(uriString);
                        startActivity(i);
                    }
                    /*
                    //for opening a website
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(uriString));
                    startActivity(i);
                    */

                    /*
                    //for opening another app
                    Intent i = this.getPackageManager().getLaunchIntentForPackage(uriString);
                    startActivity(i);*/

                    /*
                    //for opening another activity within the app
                    Class cls = Class.forName(uriString);
                    Intent i = new Intent(this, cls);
                    startActivity(i);*/
                }
                else
                {
                    mPager.setCurrentItem(1);
                }
                String pushStore = json.getString("alert");
                Toast.makeText(this, uriString, Toast.LENGTH_LONG).show();
            }
            else {
                Log.v("test","extras=null");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        tabLayout = (TabLayout) findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(mPager);

        Log.v("test page",mPager.getCurrentItem()+"");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        else if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_fetch) {
            fetch();
            return true;
        }
        else if (id == R.id.action_takeAtt) {
            Intent intent = new Intent(MainActivity.this,Test.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_push) {
            push();
            return true;
        }
        else if (id == R.id.action_results) {
            Toast.makeText(MainActivity.this, "Coming soon!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_save) {
            FetchData.update(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout()
    {
        progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Please wait");
        progress.setMessage("Logging out...");
        progress.setCancelable(false);
        progress.show();

        Login.currentUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                progress.dismiss();
                if(e==null)
                {
                    Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                    goToLogin();
                }
                else
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void fetch()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            FetchData.saveToLocal(this);
        }
        else {
                /*FetchData.fetchFromLocal(this);*/
            Log.v("test", "no internet");
        }
    }

    private void push()
    {

        AlertDialog.Builder chooserDialog = new AlertDialog.Builder(this);
        final AlertDialog.Builder dialogPush = new AlertDialog.Builder(this);
        final AlertDialog.Builder dialogMeet = new AlertDialog.Builder(this);
        chooserDialog.setTitle("Select");
        dialogPush.setTitle("Send push notification");
        dialogMeet.setTitle("Call a meet");

            /*LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);

            final EditText titleBox = new EditText(this);
            titleBox.setHint("Title");
            titleBox.setSingleLine(true);
            titleBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            layout.addView(titleBox);

            final EditText descriptionBox = new EditText(this);
            descriptionBox.setHint("Description");
            descriptionBox.setSingleLine(true);
            descriptionBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            layout.addView(descriptionBox);

            hint = "http:// (optional) (dont edit if not used)";

            final EditText uriBox = new EditText(this);
            uriBox.setText(hint);
            uriBox.setSingleLine(true);
            uriBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            layout.addView(uriBox);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(10, 10, 10, 10);

            titleBox.setLayoutParams(lp);
            descriptionBox.setLayoutParams(lp);
            uriBox.setLayoutParams(lp);*/

        final LayoutInflater inflater = this.getLayoutInflater();


        chooserDialog.setPositiveButton("Meet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                View v = inflater.inflate(R.layout.meet_menu, null);
                dialogMeet.setView(v);
                final DatePicker datePicker = (DatePicker) v.findViewById(R.id.meet_date);
                final TimePicker timePicker = (TimePicker) v.findViewById(R.id.meet_time);
                dialogMeet.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Log.v("test",datePicker.getDayOfMonth()+" "+datePicker.getMonth()+" "+datePicker.getYear()+" "+
                                timePicker.getCurrentHour()+" "+timePicker.getCurrentMinute()+"");

                    }
                });

                dialogMeet.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialogMeet.show();
            }
        });

        chooserDialog.setNeutralButton("Push", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                View v = inflater.inflate(R.layout.push_menu, null);
                dialogPush.setView(v);
                final TextView titleBox = (TextView) v.findViewById(R.id.title_push);
                final TextView descriptionBox = (TextView) v.findViewById(R.id.description_push);
                final TextView uriBox = (TextView) v.findViewById(R.id.uri_push);
                final EditText urihint = (EditText) v.findViewById(R.id.uri_push);
                hint=urihint.getText().toString();
                dialogPush.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("title", titleBox.getText().toString());
                        params.put("content", descriptionBox.getText().toString());
                        if (!uriBox.getText().toString().equals(hint)) {
                            Log.v("test", "uri entered");
                            params.put("uri", uriBox.getText().toString());
                        } else {
                            Log.v("test", "no uri entered");
                        }
                        ParseCloud.callFunctionInBackground("notify", params, new FunctionCallback<Integer>() {
                            @Override
                            public void done(Integer integer, ParseException e) {
                                if (e == null) {
                                    if (integer == 1)
                                        Log.v("test", "push sent");
                                    else
                                        Log.v("test", "push status unsure, response: " + integer);
                                } else
                                    Log.v("test", "push unsuccessful");
                            }
                        });
                    }
                });
                dialogPush.setNegativeButton("Calcel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    /*dialog.dismiss();*/
                    }
                });
                dialogPush.show();
            }
        });
        chooserDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        chooserDialog.show();

            /*LayoutInflater inflater = this.getLayoutInflater();
            dialog.setView(inflater.inflate(R.layout.push_menu, null));
            final TextView titleBox = (TextView) findViewById(R.id.title);
            final TextView descriptionBox = (TextView) findViewById(R.id.description);
            final TextView uriBox = (TextView) findViewById(R.id.uri);*/
            /*dialog.setView(layout);*/
            /*dialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("title", titleBox.getText().toString());
                    params.put("content", descriptionBox.getText().toString());
                    if (!uriBox.getText().toString().equals(hint)) {
                        Log.v("test", "uri entered");
                        params.put("uri", uriBox.getText().toString());
                    } else {
                        Log.v("test", "no uri entered");
                    }
                    ParseCloud.callFunctionInBackground("notify", params, new FunctionCallback<Integer>() {
                        @Override
                        public void done(Integer integer, ParseException e) {
                            if (e == null) {
                                if (integer == 1)
                                    Log.v("test", "push sent");
                                else
                                    Log.v("test", "push status unsure, response: " + integer);
                            } else
                                Log.v("test", "push unsuccessful");
                        }
                    });
                }
            });
            dialog.setNegativeButton("Calcel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    *//*dialog.dismiss();*//*
                }
            });
            dialog.show();*/
    }

    public class SlidePagerAdapter extends FragmentPagerAdapter {
        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
			/*
			 * IMPORTANT: This is the point. We create a RootFragment acting as
			 * a container for other fragments
			 */
            if (position == 0)
                return new RegisterRootFragment();
            else if(position == 1)
                return new announcements();
            else
                return new attendance();
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
                return "Meets";
            else if(position==1)
                return "Notifs";
            else if(position==2)
                return "Attendance";
            return "Heading";

        }

    }

    private void goToLogin() {
        // Let's go to the MainActivity

        Intent intent = new Intent(MainActivity.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
