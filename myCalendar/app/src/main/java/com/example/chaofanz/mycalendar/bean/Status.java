package com.example.chaofanz.mycalendar.bean;

/**
 * Created by Administrator on 2017/12/3.
 */

public class Status {

    // Status like 0 uncategories, 1 scheduled, 2 done, 3 delete, 4 working, 5 important, 6 repeat, 7 waiting, 8 keep, 9 others, 10 lunar calendar
    private String[] statusStr = {
            "uncategories",
            "scheduled",
            "done",
            "delete",
            "working",
            "important",
            "repeat",
            "waiting",
            "keep",
            "others",
            "lunar calendar"
    };

    public String[] getStatusStr() {
        return statusStr;
    }
}
