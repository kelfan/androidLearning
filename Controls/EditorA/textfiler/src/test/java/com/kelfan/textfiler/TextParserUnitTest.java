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
        assertEquals("<Delimiter>test</Delimiter>\n", b);
        assertEquals("test", c);
    }
    @Test
    public void getConfig() {
        Configer configer = new Configer("\n , .");
        String b = configer.toString();
        assertEquals("<delimiter>\\n , . </delimiter>\n" +
                "<level></level>\n", b);
        configer = TextParser.getConfig(b);
        String c = configer.toString();
        assertEquals(b, c);
    }

}