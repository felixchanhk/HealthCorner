package com.example.healthcorner.database.model;

public class Water {
    private String id;
    private String capicity;
    private String datetime;
    private String reminder;

    public Water() {
    }

    public Water(String id, String capicity, String datetime, String reminder) {
        this.id = id;
        this.capicity = capicity;
        this.datetime = datetime;
        this.reminder = reminder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCapicity() {
        return capicity;
    }

    public void setCapicity(String capicity) {
        this.capicity = capicity;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
