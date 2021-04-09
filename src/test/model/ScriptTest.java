package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//Unit Test for Script Class
public class ScriptTest {

    Script s1;
    Script s2;
    Block b1;

    @BeforeEach
    public void beforeEach() {
        s1 = new Script("Piano1");
        s2 = new Script(new Point(300, 256), "Script2");
        b1 = new NoteBlock("Piano", "C4", "q");
    }

    @Test
    public void testConstructorWithPoint() {
        //Test the constructor using Point
        assertEquals("Script2", s2.getScriptName());
        assertEquals(300, s2.getScriptX());
        assertEquals(250, s2.getScriptY());
        assertTrue(s2.getBlockList().isEmpty());
    }

    @Test
    public void testSetter() {
        s1.setScriptName("ScriptNew");
        assertEquals("ScriptNew", s1.getScriptName());
    }

    @Test
    public void testAddScriptX() {
        //Test if the input is added to the x value
        s2.addScriptX(20);
        assertEquals(320, s2.getScriptX());
    }

    @Test
    public void testAddScriptY() {
        //Test if the input is added to the y value
        s2.addScriptY(35);
        assertEquals(250 + 35, s2.getScriptY());
    }

    @Test
    public void testContainsRangeTrue() {
        //Test if the point fits in the range of the boundary
        assertTrue(s2.containsRange(new Point(350, 300)));
        assertTrue(s2.containsRange(new Point(300, 300)));
        assertTrue(s2.containsRange(new Point(350, 250)));

        //Add Block
        s2.addBlock(b1);
        assertTrue(s2.containsRange(new Point(350, 250 + 2 * Script.HEIGHT)));
    }

    @Test
    public void testContainsRangeFalse() {
        //Test all the cases where the point is not in the range of the boundary
        assertFalse(s2.containsRange(new Point(299, 300)));
        assertFalse(s2.containsRange(new Point(300 + Script.WIDTH + 1, 300)));
        assertFalse(s2.containsRange(new Point(300, 249)));

        //AddBlock
        s2.addBlock(b1);
        assertFalse(s2.containsRange(new Point(350, 250 + 2 * Script.HEIGHT + 1)));
    }


    @Test
    public void testAddBlockEmpty() {
        //add initialized block to s1
        s1.addBlock(b1);

        //check for the size and order of the list
        assertEquals(s1.getBlockList().size(), 1);
        assertTrue(s1.getBlockList().contains(b1));
    }

    @Test
    public void testAddBlockNotEmpty() {
        //add 2 additional blocks to s2
        Block b2 = new NoteBlock("Piano", "D4", "q");
        Block b3 = new NoteBlock("Piano", "D4", "q");
        s1.addBlock(b1);
        s1.addBlock(b2);
        s1.addBlock(b3);

        //check for the size and order of the list
        assertEquals(s1.getBlockList().size(), 3);
        assertEquals(s1.getBlockList().indexOf(b1), 0);
        assertEquals(s1.getBlockList().indexOf(b2), 1);
        assertEquals(s1.getBlockList().indexOf(b3), 2);
    }

    @Test
    public void testRemoveBlockNotInList() {
        //With s1 as b1 and remove random block not in blockList
        s1.addBlock(b1);
        Block b2 = new NoteBlock("Piano", "C4", "q");
        s1.removeBlock(b2);
        assertEquals(s1.getBlockList().size(), 1);
        assertFalse(s1.getBlockList().contains(b2));
    }

    @Test
    public void testRemoveBlockInList() {
        //With s1 as b1 and b2 and remove b1 block
        s1.addBlock(b1);
        Block b2 = new NoteBlock("Piano", "C4", "q");
        s1.addBlock(b2);
        s1.removeBlock(b1);
        assertEquals(s1.getBlockList().size(), 1);
        assertFalse(s1.getBlockList().contains(b1));
    }

    @Test
    public void testRemoveBlockBigList() {
        //With s1 as many Blocks and remove one block
        s1.addBlock(b1);
        Block b2 = new NoteBlock("Piano", "C4", "q");
        Block b3 = new NoteBlock("Piano", "C4", "q");
        s1.addBlock(b2);
        s1.addBlock(b3);
        s1.removeBlock(b2);
        assertEquals(s1.getBlockList().size(), 2);
        assertFalse(s1.getBlockList().contains(b2));
    }

    @Test
    public void testRemoveBlockDuplicateBlocks() {
        //With s1 as duplicate blocks and remove a the recent duplicate
        s1.addBlock(b1);
        Block b2 = new NoteBlock("Flute", "C4", "q");
        Block b3 = new NoteBlock("Guitar", "C4", "q");
        s1.addBlock(b2);
        s1.addBlock(b1);
        s1.addBlock(b3);
        s1.removeBlock(b1);
        assertEquals(s1.getBlockList().size(), 3);
        assertEquals(s1.getBlockList().indexOf(b1), 0);
    }


    @Test
    public void testConvertBlockOneBlock() {
        s1.addBlock(b1);
        assertEquals(s1.convertScript(), "I[Piano] C4q ");
    }

    @Test
    public void testConvertBlockMultipleBlocks() {
        //creating multiply arbitrary blocks
        Block b2 = new RestBlock("q");
        Block b3 = new NoteBlock("Flute", "G4", "h");
        s1.addBlock(b1);
        s1.addBlock(b2);
        s1.addBlock(b3);

        assertEquals(s1.convertScript(), "I[Piano] C4q Rq I[Flute] G4h ");
    }
}
