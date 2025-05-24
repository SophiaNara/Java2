package wombatdb;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WombatDBTest {

    private final ByteArrayOutputStream outResult = new ByteArrayOutputStream();
    private WombatDBapi dBapi;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outResult));
        dBapi = new WombatDBapi();
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    private void simulateInput (String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void testSplitLine() throws Exception {
        ArrayList<String> result = dBapi.splitLine("AddTable MockComponent");
        assertEquals(2, result.size());
        assertEquals("addtable", result.get(0));
        assertEquals("MockComponent", result.get(1));
    }

    @Test
    void commandLoop() {

    }

}