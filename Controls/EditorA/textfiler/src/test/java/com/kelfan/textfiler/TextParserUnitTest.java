package com.kelfan.textfiler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TextParserUnitTest {
    @Test
    public void setXmlContent() {
        String a = "test";
        String b = TextParser.setXmlContent(a, "Delimiter");
        String c = TextParser.getXmlContent(b, "Delimiter");
        assertEquals("<Delimiter>\n" +
                "test\n" +
                "</Delimiter>\n", b);
        assertEquals("test", c);
    }
}