package com.kelfan.utillibrary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ListStringTest {
    @Test
    public void list2str() {
        String a = "1_2,3,4";
        ListString listString = new ListString(a);
        String b = listString.toString();
        assertEquals(a, b);
    }
}