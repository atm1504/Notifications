package com.grobo.notifications.models;

public class TimetableItem {

    private String time;
    private String subject;

    public TimetableItem(){}

    public TimetableItem(String time, String subject){
        this.time = time;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
