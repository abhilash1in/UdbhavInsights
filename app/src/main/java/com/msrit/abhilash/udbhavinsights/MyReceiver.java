package com.msrit.abhilash.udbhavinsights;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.msrit.abhilash.udbhavinsights.TakeAttendance.Test;

import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;
/*import com.parse.TaskStackBuilderHelper;*/

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;
import java.util.Random;


/**
 * Created by Abhilash on 07/02/2016.
 */
public class MyReceiver extends ParsePushBroadcastReceiver {
/*
    @Override
    protected void onPushOpen(Context context, Intent intent) {

        *//*Log.e("Push", "Clicked");
        Intent i = new Intent(context, MainActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*//*


        ParseAnalytics.trackAppOpenedInBackground(intent);
        String uriString = null;


        try {
            JSONObject cls = new JSONObject(intent.getStringExtra("com.parse.Data"));
            uriString = cls.optString("uri", (String)null);

        } catch (JSONException var6) {
        }

        Class cls1 = this.getActivity(context, intent);
        Intent activityIntent;
        if(uriString != null) {
            activityIntent = new Intent("android.intent.action.VIEW", Uri.parse(uriString));
        } else {
            activityIntent = new Intent(context, cls1);
        }

        activityIntent.putExtras(intent.getExtras());
        if(Build.VERSION.SDK_INT >= 16) {
            taskStackBuilderHelper.startActivities(context, cls1, activityIntent);
        } else {
            activityIntent.addFlags(268435456);
            activityIntent.addFlags(67108864);
            context.startActivity(activityIntent);
        }
    }*/

/*    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Toast.makeText(context, "on receive", Toast.LENGTH_SHORT).show();
    }*/


/*
    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
        Toast.makeText(context, "on push receive", Toast.LENGTH_SHORT).show();
        *//*announcements.fetch(context);*//*

        String pushDataStr = intent.getStringExtra("com.parse.Data");
        if(pushDataStr == null) {
            Log.e("test", "Can not get push data from intent.");
        } else {
            Log.v("test", "Received push data: " + pushDataStr);
            JSONObject pushData = null;

            try {
                pushData = new JSONObject(pushDataStr);
            } catch (JSONException var8) {
                Log.e("test", "Unexpected JSONException when receiving push data: ", var8);
            }

            String action = null;
            if(pushData != null) {
                action = pushData.optString("action", (String)null);
            }

            if(action != null) {
                Bundle notification = intent.getExtras();
                Intent broadcastIntent = new Intent();
                broadcastIntent.putExtras(notification);
                broadcastIntent.setAction(action);
                broadcastIntent.setPackage(context.getPackageName());
                context.sendBroadcast(broadcastIntent);
            }

            try{
                long time = new Date().getTime();
                String tmpStr = String.valueOf(time);
                String last4Str = tmpStr.substring(tmpStr.length() - 5);
                int notificationId = Integer.valueOf(last4Str);
                JSONObject cls = new JSONObject(intent.getStringExtra("com.parse.Data"));
                Random random = new Random();
                Bundle extras = intent.getExtras();
                int contentIntentRequestCode = random.nextInt();
                int deleteIntentRequestCode = random.nextInt();
                String packageName = context.getPackageName();
                Intent contentIntent = new Intent("com.parse.push.intent.OPEN");
                contentIntent.putExtras(extras);
                contentIntent.setPackage(packageName);
                Intent deleteIntent = new Intent("com.parse.push.intent.DELETE");
                deleteIntent.putExtras(extras);
                deleteIntent.setPackage(packageName);
                PendingIntent pContentIntent = PendingIntent.getBroadcast(context, contentIntentRequestCode, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                PendingIntent pDeleteIntent = PendingIntent.getBroadcast(context, deleteIntentRequestCode, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                final Notification.Builder notif = new Notification.Builder(context);
                notif.setContentTitle(cls.getString("title"))
                        .setContentText(cls.getString("alert"))
                                //.setColor(Color.parseColor("fff")) //ok
                        .setSmallIcon(R.mipmap.push_icon2) //ok
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.push_icon2))
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setContentIntent(pContentIntent)
                        .setDeleteIntent(pDeleteIntent)
                        .setAutoCancel(true)
                        .setDefaults(-1)
                        .setStyle(new Notification.BigTextStyle().bigText("Notification"));
                NotificationManager notificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(notificationId, notif.build());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }*/

    /*@Override
    protected void onPushOpen(Context context, Intent intent) {
        if(intent.getExtras()==null)
        {
            Log.v("test","receiver intent extras = null");
        }
        else
        {
            Log.v("test","receiver intent extras not null");
        }
    }*/

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        try{
            long time = new Date().getTime();
            String tmpStr = String.valueOf(time);
            String last4Str = tmpStr.substring(tmpStr.length() - 5);
            int notificationId = Integer.valueOf(last4Str);
            JSONObject cls = new JSONObject(intent.getStringExtra("com.parse.Data"));
            Random random = new Random();
            Bundle extras = intent.getExtras();
            int contentIntentRequestCode = random.nextInt();
            int deleteIntentRequestCode = random.nextInt();
            String packageName = context.getPackageName();
            /*Intent contentIntent = new Intent("com.parse.push.intent.OPEN");
            contentIntent.putExtras(extras);
            contentIntent.setPackage(packageName);
            Intent deleteIntent = new Intent("com.parse.push.intent.DELETE");
            deleteIntent.putExtras(extras);
            deleteIntent.setPackage(packageName);*/

            Intent contentIntent = new Intent(context,MainActivity.class);
            contentIntent.putExtras(extras);
            contentIntent.setPackage(packageName);
            Intent deleteIntent = new Intent("com.parse.push.intent.DELETE");
            deleteIntent.putExtras(extras);
            deleteIntent.setPackage(packageName);


            PendingIntent pContentIntent = PendingIntent.getActivity(context, contentIntentRequestCode, contentIntent, PendingIntent.FLAG_ONE_SHOT);
            PendingIntent pDeleteIntent = PendingIntent.getBroadcast(context, deleteIntentRequestCode, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            final Notification.Builder notif = new Notification.Builder(context);
            notif.setContentTitle(cls.getString("title"))
                    .setContentText(cls.getString("alert"))
                            //.setColor(Color.parseColor("fff")) //ok
                    .setSmallIcon(R.mipmap.push_icon2) //ok
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.push_icon2))
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setContentIntent(pContentIntent)
                    .setDeleteIntent(pDeleteIntent)
                    .setAutoCancel(true)
                    .setDefaults(-1)
                    .setStyle(new Notification.BigTextStyle().bigText("Notification"));
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId, notif.build());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
