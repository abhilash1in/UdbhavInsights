package com.msrit.abhilash.udbhavinsights.Data;

/**
 * Created by Abhilash on 13/03/2016.
 */
public class EventData {
    private String event_name,date,time,venue,rules,coordinator,phone;
    int size;
    boolean inter =false; //true=interEvent, false=intraEvent

    public boolean isInter() {
        return inter;
    }

    public void setInter(boolean inter) {
        this.inter = inter;
    }

    public void setEvent_name(String event_name) {

        this.event_name = event_name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSize() {
        return size;
    }
}
