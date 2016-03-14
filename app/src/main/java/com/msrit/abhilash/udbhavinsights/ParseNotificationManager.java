package com.msrit.abhilash.udbhavinsights;

/**
 * Created by Abhilash on 10/02/2016.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;

class ParseNotificationManager {
    public static final String TAG = "com.parse.ParseNotificationManager";
    private final AtomicInteger notificationCount = new AtomicInteger(0);
    private volatile boolean shouldShowNotifications = true;

    ParseNotificationManager() {
    }

    public static ParseNotificationManager getInstance() {
        return ParseNotificationManager.Singleton.INSTANCE;
    }

    public void setShouldShowNotifications(boolean show) {
        this.shouldShowNotifications = show;
    }

    public int getNotificationCount() {
        return this.notificationCount.get();
    }

    public void showNotification(Context context, Notification notification) {
        if(context != null && notification != null) {
            this.notificationCount.incrementAndGet();
            if(this.shouldShowNotifications) {
                NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                int notificationId = (int)System.currentTimeMillis();

                try {
                    nm.notify(notificationId, notification);
                } catch (SecurityException var6) {
                    notification.defaults = 5;
                    nm.notify(notificationId, notification);
                }
            }
        }

    }

    public static class Singleton {
        private static final ParseNotificationManager INSTANCE = new ParseNotificationManager();

        public Singleton() {
        }
    }
}

