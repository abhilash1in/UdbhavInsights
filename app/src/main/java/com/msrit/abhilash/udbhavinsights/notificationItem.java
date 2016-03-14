package com.msrit.abhilash.udbhavinsights;

/**
 * Created by Abhilash on 08/02/2016.
 */
public class notificationItem {
    String title,content;

    public notificationItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
