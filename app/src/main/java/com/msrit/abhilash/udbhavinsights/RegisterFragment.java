package com.msrit.abhilash.udbhavinsights;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
    ProgressDialog dialog2;
    ParseUser pu = ParseUser.getCurrentUser();
    ArrayList<ParseObject> eventsData = new ArrayList<>();
    ArrayList<Integer> mainNameIndex = new ArrayList<>();
    TextView reg_type;
    JSONObject reg_data;
    /*ArrayList<String> classNamesForArjun = new ArrayList<>();*/

    String id;



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

        reg_type = (TextView) view.findViewById(R.id.registration_type);
        reg_type.setText("Inter College Registration");
        reg_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.register_frame, new IntraRegisterFragment());
                transaction.commit();
            }
        });
        name = (EditText) view.findViewById(R.id.name);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                for (int i = 0; i < mainNameIndex.size(); i++) {
                    allets.get(mainNameIndex.get(i)).setText(s.toString());
                    allets.get(mainNameIndex.get(i)).setEnabled(false);
                    allets.get(mainNameIndex.get(i)).setFocusable(false);
                    allets.get(mainNameIndex.get(i)).setTextColor(Color.parseColor("#000000"));
                }

            }
        });
        college = (EditText) view.findViewById(R.id.college);
        usn = (EditText) view.findViewById(R.id.usn);
        phone = (EditText) view.findViewById(R.id.phone);
        email = (EditText) view.findViewById(R.id.email);
        total = (TextView) view.findViewById(R.id.totalAmt);

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
                if(id2.getParticularEvent()!=null&&(id2.isInter()))
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
                        if(i==0)
                        {
                            mainNameIndex.add(etcount);
                        }
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
            for(int i=0;i<mainNameIndex.size();i++)
            {
                allets.get(mainNameIndex.get(i)).setEnabled(false);
                allets.get(mainNameIndex.get(i)).setFocusable(false);
                allets.get(mainNameIndex.get(i)).setTextColor(Color.parseColor("#000000"));
            }
        }

        compute();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        return view;
    }

    void dispatchMail()
    {
        try
        {
            StringBuilder body = new StringBuilder("<html><p>Hola!</p>" +
                    "<p>Thank you for registering to participate in Udbhav 2016 #circusStreet, happening from 30th March to 1st April 2016! You can find your registration details and the registration code below. The registration code will serve as your identification during the fest.&nbsp;</p>"+
                    "<p>Your registration details are as follows:</p>");
            body.append("<p>Name:&nbsp; "+na+"</p>");
            body.append("<p>USN:&nbsp; " + u + "</p>");
            body.append("<p>College:&nbsp; " + c + "</p>");
            body.append("<p>Phone:&nbsp; "+p+"</p>");
            body.append("<p>Email:&nbsp; " + e + "</p>");
            body.append("<p>Confirmation Id:&nbsp; <b>"+id+"</b></p>");
            body.append("<p><u>Event Registration Details</u></p>");
            body.append("<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px\">");
            body.append("<thead><tr>");
            body.append("<th scope=\"col\">Sl No.</th>");
            body.append("<th scope=\"col\">Event Name</th>");
            body.append("<th scope=\"col\">Participants</th>");
            body.append("</tr></thead>");
            body.append("<tbody>");
            for(int i=0;i<eventsData.size();i++)
            {
                ParseObject temp = eventsData.get(i);
                String participant_names =temp.get("participants").toString();
                participant_names = participant_names.replaceAll("\\[", "").replaceAll("\\]","");
                body.append("<tr>");

                body.append("<td>&nbsp;");
                body.append(i+1);
                body.append(".");
                body.append("</td>");

                body.append("<td>&nbsp;");
                body.append(temp.get("event_name"));
                body.append("</td>");

                body.append("<td>&nbsp;");
                body.append(participant_names);
                body.append("</td>");

                body.append("</tr>");
            }

            body.append("</tbody></table>");
            body.append("<p> For event rules, schedule, co-ordinator contact details, updates and other details, ");
            body.append("<a href=\"http://app.msritudbhav.in\"><u>download the official Udbhav 2016 android app</u></a> , or ");
            body.append("<a href=\"http://msritudbhav.in\"><u> visit Udbhav 2016 website</u></a></p>");
            body.append("<p>We hope you have a great time! Looking forward to see you real soon!</p><br>");
            body.append("<p>Regards,</p>");
            body.append("<p>Udbhav 2016</p>");
            body.append("</html>");
            String emailBody = body.toString();
            new sendMail(getActivity(),pu.getEmail(),emailBody).execute(email.getText().toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            dismiss(dialog);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Error");
            builder.setMessage("Registration unsuccessful. Try again. Error: " + e.getMessage());
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setCancelable(false);
            builder.create().show();
        }
    }

    void submit()
    {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading. Please wait...");
        dialog.setCancelable(false);
        dialog.show();


        id= RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        reg_data = new JSONObject();
        JSONArray allevents = new JSONArray();

        try {
            na = name.getText().toString();
            u = usn.getText().toString();
            c = college.getText().toString();
            p = phone.getText().toString();
            e = email.getText().toString();

            if(!isValidEmailAddress(e))
            {
                dismiss(dialog);
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                builder.setMessage("Invalid email!")
                        .setTitle("Oops!")
                        .setPositiveButton(android.R.string.ok, null)
                .setCancelable(false);
                android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }

            reg_data.put("name",na);
            reg_data.put("usn", u);
            reg_data.put("college", c);
            reg_data.put("phone", p);
            reg_data.put("email", e);
            reg_data.put("id", id);
        }
        catch (JSONException e)
        {
            dismiss(dialog);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Error");
            builder.setMessage("Registration unsuccessful. Try again. Error: " + e.getMessage());
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setCancelable(false);
            builder.create().show();
            e.printStackTrace();
        }

        for (int i=0;i<allcbs.size();i++)
        {
            if(allcbs.get(i).isChecked())
            {
                String className = allcbs.get(i).getText().toString();
                className = className.replaceAll("[^A-Za-z0-9]","");
                /*classNamesForArjun.add(className);*/
                ParseObject object = new ParseObject(className);
                JSONObject one_event = new JSONObject();
                JSONArray names = new JSONArray();
                ArrayList<String> participants = new ArrayList<>();
                try
                {
                    /*Log.v("test", allcbs.get(i).getText().toString());*/

                    if (i == 0) {
                        for (int k = 0; k < table.get(i); k++) {
                            if (allets.get(k) != null && allets.get(k).getText().toString() != "" && allets.get(k).getText().toString() != null) {

                                names.put(allets.get(k).getText().toString());
                                participants.add(allets.get(k).getText().toString());
                                /*Log.v("test", allets.get(k).getText().toString());*/
                            }
                        }
                    } else {
                        /*Log.v("test", "1. " + (table.get(i - 1)) + "  2. " + table.get(i));*/
                        for (int j = table.get(i - 1); j < table.get(i); j++) {
                            if (allets.get(j) != null && allets.get(j).getText().toString() != "" && allets.get(j).getText().toString() != null) {

                                names.put(allets.get(j).getText().toString());
                                participants.add(allets.get(j).getText().toString());
                                /*Log.v("test", allets.get(j).getText().toString());*/
                            }
                        }
                    }
                    one_event.put(allcbs.get(i).getText().toString(),names);
                    allevents.put(one_event);
                    object.put("event_name",className);
                    object.put("participants",participants);
                    object.put("name",na);
                    object.put("usn",u);
                    object.put("email",e);
                    object.put("phone",p);
                    object.put("college", c);
                    object.put("id",id);
                    eventsData.add(object);

                }
                catch (JSONException e1)
                {
                    dismiss(dialog);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Error");
                    builder.setMessage("Registration unsuccessful. Try again. Error: " + e1.getMessage());
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setCancelable(false);
                    builder.create().show();
                    e1.printStackTrace();
                }
            }
        }

        /*Log.v("testarjun",classNamesForArjun.toString());*/


        try{
            reg_data.put("events", allevents);
            reg_data.put("amount", amt);
            reg_data.put("submitted_by", pu.getString("name"));
            /*Log.v("json", reg_data.toString(4));*/
        }catch (JSONException e)
        {
            dismiss(dialog);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Error");
            builder.setMessage("Registration unsuccessful. Try again. Error: " + e.getMessage());
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setCancelable(false);
            builder.create().show();
            e.printStackTrace();
        }

        if (na.length()>0&&u.length()>0&&c.length()>0&&p.length()>0&&e.length()>0) {
            try
            {
                /*upload(reg_data);*/
                checkNetwork check = new checkNetwork();
                check.execute(getActivity());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                dismiss(dialog);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Error");
                builder.setMessage("Registration unsuccessful. Try again. Error: " + e.getMessage());
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setCancelable(false);
                builder.create().show();
            }
        }
        else
        {
            dismiss(dialog);
            Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
    }


    void compute()
    {
        /*Log.v("test", "computed!!");*/
        for(int i=1;i<table.size();i++)
        {
            table.set(i,table.get(i)+table.get(i-1));
        }
    }

    void upload(final JSONObject payload)
    {
        /*checkNetwork check = new checkNetwork();
        try
        {
            yes = check.execute(getActivity()).get();
        }
        catch (Exception e)
        {
            dismiss(dialog);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Error");
            builder.setMessage("Error. Try again \n Error: " + e.getMessage());
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setCancelable(false);
            builder.create().show();
        }*/
        /*if(yes) {*/
        if(!dialog.isShowing())
            dialog.show();
        try{
            ParseObject.saveAllInBackground(eventsData, new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.v("test", "Registration stage 1 successful");
                        final ParseObject testObject = new ParseObject("registration");
                        testObject.put("reg", payload.toString());
                        testObject.put("paid", false);
                        testObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                dismiss(dialog);
                                if (e == null) {
                                    try {
                                        dispatchMail();
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                        dismiss(dialog);
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                                        builder2.setTitle("Error");
                                        builder2.setMessage("Bad internet connection! Registration successful but email NOT sent. Please check internet connection and try submitting again. \n \n Error: " + e1.getMessage());
                                        builder2.setPositiveButton(android.R.string.ok, null);
                                        builder2.setCancelable(false);
                                        builder2.create().show();
                                    }
                                    Toast.makeText(getActivity(), "Registration successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    e.printStackTrace();
                                    Toast.makeText(getActivity(), "Registration stage 2 failed! Please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        dismiss(dialog);
/*
                                Toast.makeText(getContext(), "Bad internet connection. Registration failed. Please try submitting again", Toast.LENGTH_SHORT).show();
*/
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                        builder2.setTitle("Error");
                        builder2.setMessage("Bad internet connection! Registration unsuccessful. Please check internet connection and try submitting again. \n  Error: " + e.getMessage());
                        builder2.setPositiveButton(android.R.string.ok, null);
                        builder2.setCancelable(false);
                        builder2.create().show();
                        e.printStackTrace();
                        Log.v("test", "Registration stage 1 failed. Error: " + e.getMessage());
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
            dismiss(dialog);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Error");
            builder.setMessage("Registration unsuccessful. Try again. Error: " + e.getMessage());
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setCancelable(false);
            builder.create().show();
        }
        /*}
        else{
            dismiss(dialog);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Error");
            builder.setMessage("Bad internet connection");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setCancelable(false);
            builder.create().show();
        }*/
    }

    private boolean isNA() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /*public boolean isNetworkAvailable(Context c) {

    }*/

    private class checkNetwork extends AsyncTask<Context,Void,Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dismiss(dialog);
            dialog2 = new ProgressDialog(getActivity());
            dialog2.setMessage("Checking internet connection and uploading registration");
            dialog2.setCancelable(false);
            dialog2.show();
        }

        @Override
        protected Boolean doInBackground(Context... params) {
            if (isNA()) {
                try {
                    HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                    urlc.setRequestProperty("User-Agent", "Test");
                    urlc.setRequestProperty("Connection", "close");
                    urlc.setConnectTimeout(2000);
                    urlc.connect();
                    return (urlc.getResponseCode() == 200);
                } catch (IOException e) {
                    Log.e("test", "Error checking internet connection", e);
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            dismiss(dialog2);
            if(aBoolean)
            {
                upload(reg_data);
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Error");
                builder.setMessage("Bad internet! Check your connection and try again");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setCancelable(false);
                builder.create().show();
            }
        }
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    public void dismiss(ProgressDialog d)
    {
        if(d.isShowing())
        {
            d.dismiss();
            Log.v("test", "dialog dismissed");
        }
    }
}
