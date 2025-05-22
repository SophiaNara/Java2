package wombatdb;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WombatDBTest {

    private final ByteArrayOutputStream outResult = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outResult));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
    }

    private void simulateInput (String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void commandLoop() {

    }

}