package com.msrit.abhilash.udbhavinsights;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.parse.FunctionCallback;
import com.parse.LogOutCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONObject;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static final int NUM_ITEMS = 2;
    private Toolbar toolbar;
    ViewPager mPager;
    private TabLayout tabLayout;
    SlidePagerAdapter mPagerAdapter;
    public String hint,uriString;
    ParseUser user;
    ProgressDialog progress;
    EditText res1,res2,res3,res4;

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
                uriString = json.optString("uri", null);
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
        else if (id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("About");
            builder.setMessage("Version: " + getString(R.string.version) + "\n Release Date: " + getString(R.string.releaseDate));
            builder.create().show();
            return true;
        }
        else if (id == R.id.action_push) {
            if(user.getBoolean("push_auth"))
            {
                if(isNetworkAvailable())
                    push();
                else
                    Toast.makeText(MainActivity.this, "Not connected to the internet", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(MainActivity.this, "Un-authorized to send push notifications", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_results) {

            if(user.getBoolean("results_auth"))
            {
                if(isNetworkAvailable())
                    uploadResults();
                else
                    Toast.makeText(MainActivity.this, "Not connected to the internet", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(MainActivity.this, "Un-authorized to upload results", Toast.LENGTH_SHORT).show();
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

        if(isNetworkAvailable())
        {
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    progress.dismiss();
                    if (e == null) {
                        Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                        goToLogin();
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else {
            Toast.makeText(MainActivity.this, "Connect to the internet and try again", Toast.LENGTH_SHORT).show();

        }
    }

    private void uploadResults()
    {
        final LayoutInflater inflater = this.getLayoutInflater();

        final AlertDialog.Builder resulsDialog = new AlertDialog.Builder(this);
        View v = inflater.inflate(R.layout.results_menu, null);
        resulsDialog.setView(v);
        res1 = (EditText) v.findViewById(R.id.eve);
        res2 = (EditText) v.findViewById(R.id.firstPlace);
        res3 = (EditText) v.findViewById(R.id.secondPlace);
        res4 = (EditText) v.findViewById(R.id.thirdPlace);

        resulsDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final ProgressDialog d = new ProgressDialog(MainActivity.this);
                d.setMessage("Uploading results...");
                d.show();

                ParseObject result = new ParseObject("results");
                result.put("event_name",res1.getText().toString());
                result.put("first",res2.getText().toString());
                result.put("second",res3.getText().toString());
                result.put("third", res4.getText().toString());

                result.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        d.dismiss();
                        if(e==null)
                        {
                            Toast.makeText(MainActivity.this, "Result uploaded successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Result uploading failed", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });

        resulsDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        resulsDialog.show();

    }

    private void push()
    {

        AlertDialog.Builder chooserDialog = new AlertDialog.Builder(this);
        final AlertDialog.Builder dialogPush = new AlertDialog.Builder(this);
        final AlertDialog.Builder dialogMeet = new AlertDialog.Builder(this);
        chooserDialog.setTitle("Select");
        dialogPush.setTitle("Send push notification");
        dialogMeet.setTitle("Call a meet");

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
            else
                return new announcements();

        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
                return "Registration";
            else
                return "Notifs";

        }

    }

    private void goToLogin() {
        // Let's go to the MainActivity

        Intent intent = new Intent(MainActivity.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
