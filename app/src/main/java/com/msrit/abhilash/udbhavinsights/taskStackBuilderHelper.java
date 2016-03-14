package com.msrit.abhilash.udbhavinsights;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;


public class taskStackBuilderHelper {
    taskStackBuilderHelper() {
    }

    public static void startActivities(Context context, Class<? extends Activity> cls, Intent activityIntent) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(activityIntent);
        stackBuilder.startActivities();
    }
}
