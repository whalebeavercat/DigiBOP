package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Unit Test for NoteBlock Class
class NoteBlockTest {

    NoteBlock nb1;
    NoteBlock nb2;

    @BeforeEach
    void beforeEach() {
        nb1 = new NoteBlock("Piano", "C4","q");
        nb2 = new NoteBlock("Guitar", "D5","h");
    }

    @Test
    void testConstructor() {
        assertEquals("Piano", nb1.getInstrument());
        assertEquals("C4", nb1.getPitch());
        assertEquals("q", nb1.getDuration());
    }

    @Test
    void testSetter() {
        nb1.setInstrument("Banjo");
        nb1.setPitch("D7");
        nb1.setDuration("w");
        assertEquals("Banjo", nb1.getInstrument());
        assertEquals("D7", nb1.getPitch());
        assertEquals("w", nb1.getDuration());
    }

    @Test
    void testConvertBlock() {
        assertEquals(nb1.convertBlock(), "I[Piano] C4q");
        assertEquals(nb2.convertBlock(), "I[Guitar] D5h");
    }
}