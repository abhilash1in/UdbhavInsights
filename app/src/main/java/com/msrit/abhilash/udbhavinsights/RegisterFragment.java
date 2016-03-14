package com.msrit.abhilash.udbhavinsights;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.msrit.abhilash.udbhavinsights.Data.Data;
import com.msrit.abhilash.udbhavinsights.Data.ItemData;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Abhilash on 13/03/2016.
 */
public class RegisterFragment extends android.support.v4.app.Fragment {

    Button register;
    EditText name,usn,college,phone,email;
    TextView total;
    int amt=0;

    final static ArrayList<ItemData> categories = Data.getCategories();
    TextView[] tv = new TextView[categories.size()];
    List<CheckBox> allcbs =  new ArrayList<>();
    List<EditText> allets =  new ArrayList<>();
    int n=0,etcount=0,cbcount=0;
    ArrayList<Integer> table = new ArrayList<>();
    String na,u,c,p,e;
    ProgressDialog dialog;
    ParseUser pu = ParseUser.getCurrentUser();
    ArrayList<ParseObject> eventsData = new ArrayList<>();
    ArrayList<String> event_names = new ArrayList<>();



    public RegisterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.register2, container, false);

        name = (EditText) view.findViewById(R.id.name);
        college = (EditText) view.findViewById(R.id.college);
        usn = (EditText) view.findViewById(R.id.usn);
        phone = (EditText) view.findViewById(R.id.phone);
        email = (EditText) view.findViewById(R.id.email);
        total = (TextView) view.findViewById(R.id.totalAmt);

        /*t1 = (TextView) view.findViewById(R.id.tvForm1);
        t2 = (TextView) view.findViewById(R.id.tvForm2);
        t3 = (TextView) view.findViewById(R.id.tvForm3);

        c1 = (CheckBox) view.findViewById(R.id.event1);
        c2 = (CheckBox) view.findViewById(R.id.event2);
        c3 = (CheckBox) view.findViewById(R.id.event3);


        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    t1.setVisibility(View.VISIBLE);
                }
                else
                {
                    t1.setVisibility(View.GONE);
                }
            }
        });

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    t2.setVisibility(View.VISIBLE);
                } else {
                    t2.setVisibility(View.GONE);
                }
            }
        });

        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    t3.setVisibility(View.VISIBLE);
                } else {
                    t3.setVisibility(View.GONE);
                }
            }
        });*/


        register = (Button) view.findViewById(R.id.register);
        final LinearLayout ll = (LinearLayout) view.findViewById(R.id.list);
        Iterator<ItemData> it  = categories.iterator();




        while(it.hasNext())
        {

            final ItemData id = it.next();
            tv[n] = new TextView(getContext());
            tv[n].setText(id.getTitle());
            ll.addView(tv[n]);
            n++;


            final LinearLayout cbll = new LinearLayout(getContext());
            cbll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            cbll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(cbll);

            Iterator<ItemData> it2  = id.getEvents().iterator();
            while (it2.hasNext())
            {
                final ItemData id2= it2.next();
                if(id2.getParticularEvent()!=null&&id2.isInter())
                {
                    CheckBox cb = new CheckBox(getContext());
                    cb.setText(id2.getTitle());
                    cbcount++;


                    final LinearLayout etll = new LinearLayout(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(50, 10, 10, 0);
                    etll.setLayoutParams(layoutParams);
                    etll.setOrientation(LinearLayout.VERTICAL);
                    etll.setId(R.id.etllid);
                    etll.setVisibility(View.GONE);

                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (!isChecked) {
                                amt-=id2.getAmt();
                                etll.setVisibility(View.GONE);
                            } else {
                                amt+=id2.getAmt();
                                etll.setVisibility(View.VISIBLE);
                                etll.requestFocus();
                            }
                            total.setText(""+amt);
                        }
                    });

                    allcbs.add(cb);
                    cbll.addView(cb);
                    cbll.addView(etll);

                    int p_size = id2.getParticularEvent().getSize();
                    table.add(p_size);
                    for(int i=0;i<p_size;i++)
                    {
                        etcount++;
                        LinearLayout etline = new LinearLayout(getContext());
                        etline.setOrientation(LinearLayout.HORIZONTAL);
                        TextView tvline = new TextView(getContext());
                        tvline.setText(""+(i+1)+". ");
                        etline.addView(tvline);
                        EditText et = new EditText(getContext());
                        et.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
                        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                        et.setSingleLine(true);
                        allets.add(et);
                        etline.addView(et);
                        etll.addView(etline);
                    }

                }
            }
        }

        compute();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                dispatchMail();
            }
        });
        return view;
    }

    void dispatchMail()
    {
        new sendMail(getContext(),pu.getEmail()).execute(email.getText().toString());
    }

    void submit()
    {
        String id= RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        JSONObject reg_data = new JSONObject();
        JSONArray reg_events = new JSONArray();
        JSONArray allevents = new JSONArray();

        try {
            na = name.getText().toString();
            u = usn.getText().toString();
            c = college.getText().toString();
            p = phone.getText().toString();
            e = email.getText().toString();
            reg_data.put("name",na);
            reg_data.put("usn", u);
            reg_data.put("college", c);
            reg_data.put("phone", p);
            reg_data.put("email", e);
            reg_data.put("id", id);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


        /*for(int i=0;i<tv.length;i++)
        {
            Log.v("test",""+i+tv[i].getText().toString());
        }*/


        for (int i=0;i<allcbs.size();i++)
        {
            if(allcbs.get(i).isChecked())
            {
                String className = allcbs.get(i).getText().toString();
                className = className.replaceAll("[^A-Za-z]","");
                ParseObject object = new ParseObject(className);
                JSONObject one_event = new JSONObject();
                JSONArray names = new JSONArray();
                try
                {
                /*Log.v("test","Heading"+i+". "+allcbs.get(i).getText().toString());*/
                    Log.v("test", allcbs.get(i).getText().toString());

                    if (i == 0) {
                        for (int k = 0; k < table.get(i); k++) {
                            if (allets.get(k) != null && allets.get(k).getText().toString() != "" && allets.get(k).getText().toString() != null) {
                        /*Log.v("test","enter inner IF");*/
                        /*Log.v("test","Event"+j+". "+allets.get(j).getText().toString());*/
                                names.put(allets.get(k).getText().toString());
                                Log.v("test", allets.get(k).getText().toString());
                            }
                        }
                    } else {
                        Log.v("test", "1. " + (table.get(i - 1)) + "  2. " + table.get(i));
                        for (int j = table.get(i - 1); j < table.get(i); j++) {
                    /*Log.v("test","enter inner for loop");*/
                            if (allets.get(j) != null && allets.get(j).getText().toString() != "" && allets.get(j).getText().toString() != null) {
                        /*Log.v("test","enter inner IF");*/
                        /*Log.v("test","Event"+j+". "+allets.get(j).getText().toString());*/
                                names.put(allets.get(j).getText().toString());
                                Log.v("test", allets.get(j).getText().toString());
                            }
                        }
                    }
                    one_event.put(allcbs.get(i).getText().toString(),names);
                    allevents.put(one_event);
                    object.put("participants",one_event.toString());
                    object.put("name",na);
                    object.put("usn",u);
                    object.put("email",e);
                    object.put("phone",p);
                    object.put("college",c);
                    object.put("id",id);
                    eventsData.add(object);
                    /*reg_data.put(allcbs.get(i).getText().toString(),names);*/

                }
                catch (JSONException e1)
                {
                    e1.printStackTrace();
                }
            }
        }


        try{
            reg_data.put("events", allevents);
            reg_data.put("amount", amt);
            reg_data.put("submitted_by", pu.getString("name"));
            Log.v("json", reg_data.toString(4));
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        if (na.length()>0&&u.length()>0&&c.length()>0&&p.length()>0&&e.length()>0) {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading. Please wait...");
            dialog.setCancelable(false);
            dialog.show();
            upload(reg_data);
        }
        else
        {
            Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
        /*Log.v("test","total et="+etcount);
        Log.v("test", "total cb="+cbcount);


        for(int i=0;i<table.size();i++)
        {
            Log.v("test", "" + i + ". " + table.get(i).toString());
        }*/

    }


    void compute()
    {
        Log.v("test", "computed!!");
        for(int i=1;i<table.size();i++)
        {
            table.set(i,table.get(i)+table.get(i-1));
        }
    }

    void upload(JSONObject payload)
    {
        if(isNetworkAvailable()) {

            try{
                ParseObject.saveAllInBackground(eventsData, new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Log.v("test","Registration stage 1 successful");
                        }
                        else
                        {
                            e.printStackTrace();
                            Log.v("test","Registration stage 1 error "+e.getMessage());

                        }

                    }
                });
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            final ParseObject testObject = new ParseObject("registration");
            testObject.put("reg", payload.toString());
            testObject.put("paid",false);
            testObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    dialog.dismiss();
                    if (e == null) {
                        Toast.makeText(getActivity(), "Registration stage 2 successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Registration failed! Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            dialog.dismiss();
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
