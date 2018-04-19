package com.kelfan.utillibrary;

import java.util.Arrays;

/**
 * Created by Administrator on 19/03/2018.
 */

public class ListWorker {
    Boolean contain(String[] list, String item) {
        return Arrays.asList(list).contains(item);
    }
}
