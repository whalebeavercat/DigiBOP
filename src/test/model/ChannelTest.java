package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Unit Test for Channel Class
public class ChannelTest {

    Channel c1;
    Script s1;

    @BeforeEach
    public void beforeEach() {
        c1 = new Channel();
        s1 = new Script("Script1");
    }

    @Test
    void testContainsScriptNoNameInList() {
        //No name in list
        Script s2 = new Script("Script2");
        c1.addScript(s1);
        c1.addScript(s2);

        assertFalse(c1.containsScriptName("Script3"));
    }

    @Test
    void testContainsScriptOneNameInList() {
        //One name in list
        Script s2 = new Script("Script2");
        c1.addScript(s1);
        c1.addScript(s2);

        assertTrue(c1.containsScriptName("Script2"));
    }

    @Test
    void testAccessScriptInScript() {
        //See if we can access the script from a channel with 3 scripts
        Script s2 = new Script("Script2");
        Script s3 = new Script("Script3");
        c1.addScript(s1);
        c1.addScript(s2);
        c1.addScript(s3);

        assertEquals(c1.accessScript("Script1"), s1);
        assertEquals(c1.accessScript("Script2"), s2);
        assertEquals(c1.accessScript("Script3"), s3);
    }

    @Test
    void testAddScriptEmpty() {
        //add s1 to empty c1
        c1.addScript(s1);
        assertEquals(c1.channelSize(), 1);
        assertTrue(c1.getScriptList().contains(s1));
    }

    @Test
    void testAddScriptMultipleScripts() {
        //add multiple scripts to see if they are added to the end
        Script s2 = new Script("Script2");
        Script s3 = new Script("Script3");
        c1.addScript(s1);
        c1.addScript(s2);
        c1.addScript(s3);

        assertEquals(c1.channelSize(), 3);
        assertEquals(c1.getScriptList().get(0), s1);
        assertEquals(c1.getScriptList().get(1), s2);
        assertEquals(c1.getScriptList().get(2), s3);
    }

    @Test
    void testRemoveScript() {
        Script s2 = new Script("Script2");
        Script s3 = new Script("Script3");
        c1.addScript(s1);
        c1.addScript(s2);
        c1.addScript(s3);
        c1.removeScript(s2);
        assertEquals(c1.channelSize(), 2);
        assertFalse(c1.containsScriptName("Script2"));
    }

    @Test
    void testChannelSize() {
        Script s2 = new Script("Script2");
        Script s3 = new Script("Script3");
        c1.addScript(s1);
        c1.addScript(s2);
        c1.addScript(s3);

        assertEquals(c1.channelSize(), 3);
    }

    @Test
    void testGetScriptNames() {
        c1.addScript(s1);
        Script s2 = new Script("Script2");
        c1.addScript(s2);
        ArrayList<String> testScriptList = (ArrayList<String>) c1.getScriptNames();
        assertTrue(testScriptList.contains("Script1"));
        assertTrue(testScriptList.contains("Script2"));
        assertEquals(testScriptList.size(), 2);
        
    }

    @Test
    void testConvertChannelEmpty() {
        assertEquals(c1.convertChannel(), "T90 ");
    }

    @Test
    void testConvertChannelOneScript() {
        Block b1 = new NoteBlock("Piano", "C4", "q");
        s1.addBlock(b1);
        c1.addScript(s1);
        c1.setBpm(150);
        assertEquals(c1.convertChannel(), "T150 V0 I[Piano] C4q ");
    }

    @Test
    void testConvertChannelMultipleScripts() {
        Block b1 = new NoteBlock("Piano", "C4", "q");
        Block b2 = new NoteBlock("Piano", "C5", "q");
        s1.addBlock(b1);
        Script s2 = new Script("Script2");
        s2.addBlock(b2);
        c1.addScript(s1);
        c1.addScript(s2);
        c1.setBpm(95);

        assertEquals(c1.convertChannel(), "T95 V0 I[Piano] C4q V1 I[Piano] C5q ");
    }

}