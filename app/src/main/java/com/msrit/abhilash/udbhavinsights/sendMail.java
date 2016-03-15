package com.msrit.abhilash.udbhavinsights;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;

/**
 * Created by Abhilash on 13/03/2016.
 */
public class sendMail extends AsyncTask<String,Void, ClientResponse>{

    String e;
    String emailBody;
    Context context;
    public sendMail(Context context, String e,String emailBody) {
        this.e=e;
        this.context = context;
        this.emailBody = emailBody;
    }

    @Override
    protected ClientResponse doInBackground(String... params) {
        try
        {
            if(isNetworkAvailable())
            {
                Client client = Client.create();
                client.addFilter(new HTTPBasicAuthFilter("api",
                        "key-cba8a0cb565dc4a9bfef0f1713c67fdd"));
                WebResource webResource =
                        client.resource("https://api.mailgun.net/v3/msritevents.in" +
                                "/messages");
                MultivaluedMapImpl formData = new MultivaluedMapImpl();
                formData.add("o:skip-verification",true);
                formData.add("from", "Udbhav 2016 <udbhav@msritevents.in>");
                formData.add("h:Reply-To","abhilash1in@gmail.com");
                formData.add("to",params[0]);
                formData.add("to",e);
                formData.add("to","reg.udbhav16@gmail.com");
                formData.add("subject", "Udbhav 2016 Registration Confirmation");
                formData.add("html", emailBody);
                return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                        post(ClientResponse.class, formData);
            }
            else
            {
                Log.v("test","no internet for email");
            }
        }
        catch(Exception e)
        {
            try{
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Error");
                builder.setMessage("Bad internet connection! Registration unsuccessful");
                builder.create().show();
            }
            catch(Exception e1)
            {
                Log.v("test",e1.getMessage());
            }

        }
        return null;

    }

    @Override
    protected void onPostExecute(ClientResponse clientResponse) {
        super.onPostExecute(clientResponse);


        if(clientResponse==null)
        {
            return;
        }
        if(clientResponse.getStatus()==200)
        {
            Toast.makeText(context, "Confirmation e-mail sent successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "ERROR! E-mail not sent", Toast.LENGTH_SHORT).show();
        }

        Log.v("test", ""+clientResponse.getStatus());
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}