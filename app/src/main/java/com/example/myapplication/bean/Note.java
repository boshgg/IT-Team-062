package com.example.myapplication.bean;

public class Note {

    private int id;
    private String cid;
    private String event;
    private String date;
    private String addnote;
    private String type;

    public Note() {
        this.id = id;
        this.cid = cid;
        this.event = event;
        this.date = date;
        this.addnote = addnote;
        this.type = type;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddnote() {
        return addnote;
    }

    public void setAddnote(String addnote) {
        this.addnote = addnote;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
