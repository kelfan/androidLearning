package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 17/01/2018.
 */

public class TimeWorker {

    public static String getDate() {
        return new SimpleDateFormat("yyyy-mm-dd").format(new Date());
    }

    public static String getDatetime() {
        return new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
    }

}
