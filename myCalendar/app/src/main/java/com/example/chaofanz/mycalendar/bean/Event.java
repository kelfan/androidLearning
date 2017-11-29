package com.example.chaofanz.mycalendar.bean;

import com.example.chaofanz.mycalendar.utils.TimeHandler;

import java.util.Date;

/**
 * Created by vifon on 2017/11/17.
 */

public class Event {
    private int id,status,repeat_type, level;
    private String content, detail, genre, location;
    private Date plan_start, plan_end, created, completed;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", status=" + status +
                ", repeat_type=" + repeat_type +
                ", level=" + level +
                ", content='" + content + '\'' +
                ", detail='" + detail + '\'' +
                ", genre='" + genre + '\'' +
                ", location='" + location + '\'' +
                ", plan_start=" + plan_start +
                ", plan_end=" + plan_end +
                ", created=" + created +
                ", completed=" + completed +
                '}';
    }

    public String getStartDay(){
        return TimeHandler.getDayString(plan_start);
    }

    public Event(int id, int status, int repeat_type, int level, String content, String detail, String genre, String location, Date plan_start, Date plan_end, Date created, Date completed) {
        this.id = id;
        this.status = status; // status like 0 uncategories, 1 scheduled, 2 done, 3 delete, 4 working, 5 important, 6 repeat, 7 waiting, 8 keep, 9 others, 10 lunar calendar
        this.repeat_type = repeat_type; // 0 non-repeat 1 yearly, 2 monthly, 3 weekly, 4 each workday, more number means repeat several days in a week like 13 means repeat each Monday & Wednesday
        this.level = level; // the higher the number the bigger the things
        this.content = content;
        this.detail = detail;
        this.genre = genre; // default others
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
