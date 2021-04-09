package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Represents a unit test for reader that reads JSON representation of channel from file
 * CITATION: code taken and modified from JsonReaderTest.java in JsonSerializationDemo
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

class JsonReaderTest {

    @Test
    void testReadInvalidFile() {
        //Read file that does not exist or invalid and catch IOException
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            Channel c = reader.read();
            fail("IOException should be thrown");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testReadEmptyChannel() {
        //Read json file with an empty Channel
        JsonReader reader = new JsonReader("./data/testReadEmptyChannel.json");
        try {
            Channel c = reader.read();
            assertEquals(90, c.getBpm());
            assertEquals(c.channelSize(), 0);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testReadChannelWithEmptyScripts() {
        //Read a json file with a channel and an empty script
        JsonReader reader = new JsonReader("./data/testReadChannelWithEmptyScripts.json");
        try {
            Channel c = reader.read();
            assertEquals(90, c.getBpm());
            assertEquals(c.channelSize(), 1);
            assertEquals(c.accessScript("Script").getBlockList().size(), 0);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testReadChannelWithScripts() {
        //Read a json file with a channel, scripts, and blocks
        try {
            JsonReader reader = new JsonReader("./data/testReadChannelWithScripts.json");
            Channel c = reader.read();
            assertEquals(90, c.getBpm());
            assertEquals(c.channelSize(), 2);
            List<Block> blockList1 = c.accessScript("Script1").getBlockList();
            List<Block> blockList2 = c.accessScript("Script2").getBlockList();
            assertEquals(blockList1.size(), 3);
            assertEquals(blockList2.size(), 1);
            assertEquals(blockList1.get(0).convertBlock(), "I[Guitar] C4h C4h C4h C4h");
            assertEquals(blockList1.get(1).convertBlock(), "I[Piano] G6w");
            assertEquals(blockList1.get(2).convertBlock(), "Rw");
            assertEquals(blockList2.get(0).convertBlock(), "Rh");
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

}