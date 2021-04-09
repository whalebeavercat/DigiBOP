package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit Test for LoopBlock
class LoopBlockTest {

    LoopBlock b1;
    LoopBlock b2;

    @BeforeEach
    void beforeEach() {
        b1 = new LoopBlock("Piano", "C4", "q", 3);
        b2 = new LoopBlock("Flute", "D4", "h", 5);
    }

    @Test
    void testConstructor() {
        assertEquals("Piano", b1.getInstrument());
        assertEquals("C4", b1.getPitch());
        assertEquals("q", b1.getDuration());
        assertEquals(3, b1.getLoop());
    }

    @Test
    void testSetter() {
        b1.setInstrument("Banjo");
        b1.setPitch("D7");
        b1.setDuration("w");
        b1.setLoop(6);
        assertEquals("Banjo", b1.getInstrument());
        assertEquals("D7", b1.getPitch());
        assertEquals("w", b1.getDuration());
        assertEquals(6, b1.getLoop());
    }

    @Test
    void testConvertBlock() {
        assertEquals(b1.convertBlock(), "I[Piano] C4q C4q C4q");
        assertEquals(b2.convertBlock(), "I[Flute] D4h D4h D4h D4h D4h");
    }

}