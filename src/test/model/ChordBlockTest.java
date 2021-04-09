package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit Test for ChordBlock
class ChordBlockTest {

    ChordBlock cb1;
    ChordBlock cb2;

    @BeforeEach
    void beforeEach() {
        cb1 = new ChordBlock("Piano", "C4","q", "maj7");
        cb2 = new ChordBlock("Guitar", "D5","h", "min");
    }

    @Test
    void testConstructor() {
        assertEquals("Piano", cb1.getInstrument());
        assertEquals("C4", cb1.getPitch());
        assertEquals("q", cb1.getDuration());
        assertEquals("maj7", cb1.getChordType());
    }

    @Test
    void testSetter() {
        cb1.setInstrument("Banjo");
        cb1.setPitch("D7");
        cb1.setDuration("w");
        cb1.setChordType("maj");
        assertEquals("Banjo", cb1.getInstrument());
        assertEquals("D7", cb1.getPitch());
        assertEquals("w", cb1.getDuration());
        assertEquals("maj", cb1.getChordType());
    }

    @Test
    void testConvertBlock() {
        assertEquals(cb1.convertBlock(), "I[Piano] C4maj7q");
        assertEquals(cb2.convertBlock(), "I[Guitar] D5minh");
    }
}