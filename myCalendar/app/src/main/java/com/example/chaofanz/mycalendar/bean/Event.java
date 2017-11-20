package com.example.chaofanz.mycalendar.bean;

import java.util.Date;

/**
 * Created by vifon on 2017/11/17.
 */

public class Event {
    private int id,status,repeat_type, level;
    private String content, detail, genre, location;
    private Date plan_start, plan_end, created, completed;

    public Event() {
    }

    public Event(int id, int status, int repeat_type, int level, String content, String detail, String genre, String location, Date plan_start, Date plan_end, Date created, Date completed) {
        this.id = id;
        this.status = status;
        this.repeat_type = repeat_type;
        this.level = level;
        this.content = content;
        this.detail = detail;
        this.genre = genre;
        this.location = location;
        this.plan_start = plan_start;
        this.plan_end = plan_end;
        this.created = created;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRepeat_type() {
        return repeat_type;
    }

    public void setRepeat_type(int repeat_type) {
        this.repeat_type = repeat_type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getPlan_start() {
        return plan_start;
    }

    public void setPlan_start(Date plan_start) {
        this.plan_start = plan_start;
    }

    public Date getPlan_end() {
        return plan_end;
    }

    public void setPlan_end(Date plan_end) {
        this.plan_end = plan_end;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }
}
