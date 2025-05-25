package wombatdb;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WombatDBTest {

    private final ByteArrayOutputStream outResult = new ByteArrayOutputStream();
    private WombatDB db;

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outResult));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    void input(String input) {
        input = input + "\nquit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    @DisplayName("Test addtable command")
    void testAddtable() {
        input("addtable Wombat");
        new WombatDB().commandLoop();
        String output = outResult.toString();
//         assertEquals("Starting command loop\n" +
//                 "1? Command = addtable\n" +
//                 "Parameter 1 = Wombat\n" +
//                 "2? Command = quit", output);
        assertTrue(output.contains("Command = addtable"));
        assertTrue(output.contains("Parameter 1 = Wombat"));

    }

    @Test
    @DisplayName("Test do_insert(input）command")
    void testDo_insert() {
        input("addtable Wombat\ninsert Wombat 001 Alice");
        new WombatDB().commandLoop();
        String output = outResult.toString();
        assertAll(
                () -> assertTrue(output.contains("Parameter 1 = Wombat")),
                () -> assertTrue(output.contains("Parameter 2 = 001")),
                () -> assertTrue(output.contains("Parameter 3 = Alice"))
        );

//                 assertEquals("Starting command loop\n"+
//                         "1? Command = addtable\n"+
//                         "Parameter 1 = Wombat\n"+
//                         "2? Command = insert\n"+
//                         "Parameter 1 = Wombat\n"+
//                         "Parameter 2 = 001\n"+
//                         "Parameter 3 = Alice", output);
    }






}