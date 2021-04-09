package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit Test for RestBlock Class
class RestBlockTest {

    RestBlock b1;
    RestBlock b2;

    @BeforeEach
    void beforeEach() {
        b1 = new RestBlock("q");
        b2 = new RestBlock("h");
    }

    @Test
    void testConstructor() {
        assertEquals("q", b1.getDuration());
    }

    @Test
    void testSetter() {
        b1.setDuration("q");
        assertEquals("q", b1.getDuration());
    }

    @Test
    void testConvertBlock() {
        assertEquals(b1.convertBlock(), "Rq");
        assertEquals(b2.convertBlock(), "Rh");
    }
}