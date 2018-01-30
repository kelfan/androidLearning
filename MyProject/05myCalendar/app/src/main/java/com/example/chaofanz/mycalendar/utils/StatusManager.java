package com.example.chaofanz.mycalendar.utils;

import android.content.Context;

import com.example.chaofanz.mycalendar.bean.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */

public class StatusManager {
    private static Status status;
    public static Status getInstance() {
        if(status==null){
            status = new Status();
        }
        return status;
    }

    public static List<String> getStatusList(){
        status = getInstance();
        List<String> list = new ArrayList<>();
        for (int i=0; i<status.getStatusStr().length;i++){
            list.add(status.getStatusStr()[i]);
        }
        return list;
    }

}
