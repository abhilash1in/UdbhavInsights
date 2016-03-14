package com.msrit.abhilash.udbhavinsights;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;


import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONObject;

import java.util.Date;
import java.util.Random;


/**
 * Created by Abhilash on 07/02/2016.
 */
public class MyReceiver extends ParsePushBroadcastReceiver {

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
