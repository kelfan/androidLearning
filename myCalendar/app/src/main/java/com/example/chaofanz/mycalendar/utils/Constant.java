package com.example.chaofanz.mycalendar.utils;

/**
 * Created by Administrator on 2017/11/20.
 */

public class Constant {
    public static final String EVENT_ITEM_INTENT="EventItem";

    //database
    public static final int DATABASE_VER = 1;
    public static final String DATABASE_NAME = "MYCALENDAR.db";
    public static final String TABLE_NAME = "Events";            // it is the table to store information of events

    // table
    public static final String EVENT_ID = "Event_Id";            // each event has a unique id
    public static final String EVENT_CONTENT = "Event_Content"; // to record the content of event
    public static final String EVENT_DETAIL = "Event_Detail";   // add more content if need more details
    public static final String EVENT_LOCATION = "Event_Location";// stored the locations where the Event actually occur
    public static final String EVENT_LEVEL = "Event_Level";        // stored the level of the Event to filter the Event from small things; the higher the number the bigger the things
    public static final String PLAN_START = "Plan_Start";        // to record the datetime of event start; 00:00 means whole day event
    public static final String PLAN_END = "Plan_End";            //  to record the datetime of event end
    public static final String CREATE_DATE = "Create_Date";      // automatically record the datetime of event create
    public static final String COMPLETED_DATE = "Complete_Date"; // record date of complete
    public static final String EVENT_GENRE = "Event_Genre";      // put events into different level or category like Life or Class412
    public static final String EVENT_STATUS = "Event_Status";    // put event into different status like 0 uncategories, 1 scheduled, 2 done, 3 delete, 4 working, 5 important, 6 repeat, 7 waiting, 8 keep, 9 others, 10 lunar calendar
    public static final String REPEAT_TYPE = "Repeat_Type";      // event can be repeated by 1 yearly, 2 monthly, 3 weekly, 4 each workday, more number means repeat several days in a week like 13 means repeat each Monday & Wednesday and 234  means repeat on Mon,Tue & Wed

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss z";
    public static final String DATETIME_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_TIME_FORMAT = "HH:mm";
    public static final String DATETIME_PRESENT = "MM/dd HH:mm";
}
