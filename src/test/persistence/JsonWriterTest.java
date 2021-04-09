package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Represents a unit test for writer that writes JSON representation of channel to file
 * CITATION: code taken and modified from JsonWriterTest.java in JsonSerializationDemo
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

class JsonWriterTest {
    Channel c;

    @BeforeEach
    void beforeEach() {
        c = new Channel();
    }

    @Test
    void testWriteFileInvalid() {
        //Test if the JsonWriter can capture IOException from opening invalid file
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteEmptyChannel() {
        //Test if the JsonWriter can create a Json file from an empty channel
        try {
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyChannel.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyChannel.json");
            c = reader.read();
            assertEquals(90, c.getBpm());
            assertEquals(c.channelSize(), 0);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteChannelWithEmptyScript() {
        //Test if the JsonWriter can create Json file with a channel with empty script
        try {
            JsonWriter writer = new JsonWriter("./data/testWriteChannelWithEmptyScript.json");
            Script emptyScript = new Script("Script1");
            c.addScript(emptyScript);
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteChannelWithEmptyScript.json");
            c = reader.read();
            assertEquals(90, c.getBpm());
            assertEquals(c.channelSize(), 1);
            assertEquals(c.accessScript("Script1").getBlockList().size(), 0);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteChannelWithScripts() {
        //Test if the JsonWriter can create a Json file with channel with scripts and blocks
        try {
            JsonWriter writer = new JsonWriter("./data/testWriteChannelWithScripts.json");
            Script script1 = new Script("Script1");
            Script script2 = new Script("Script2");
            Block b1 = new LoopBlock("Piano", "C4", "q", 4);
            Block b2 = new NoteBlock("Piano", "G5", "w");
            Block b3 = new RestBlock("h");
            Block b4 = new ChordBlock("Piano", "C4", "q", "maj");
            c.addScript(script1);
            c.addScript(script2);
            script1.addBlock(b1);
            script1.addBlock(b2);
            script2.addBlock(b3);
            script2.addBlock(b4);
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteChannelWithScripts.json");
            c = reader.read();
            assertEquals(90, c.getBpm());
            assertEquals(c.channelSize(), 2);
            List<Block> blockList1 = c.accessScript("Script1").getBlockList();
            List<Block> blockList2 = c.accessScript("Script2").getBlockList();
            assertEquals(blockList1.size(), 2);
            assertEquals(blockList2.size(), 2);
            assertEquals(blockList1.get(0).convertBlock(), b1.convertBlock());
            assertEquals(blockList1.get(1).convertBlock(), b2.convertBlock());
            assertEquals(blockList2.get(0).convertBlock(), b3.convertBlock());
            assertEquals(blockList2.get(1).convertBlock(), b4.convertBlock());
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

}