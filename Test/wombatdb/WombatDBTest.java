package wombatdb;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WombatDBTest {

    private final ByteArrayOutputStream outResult = new ByteArrayOutputStream();
    private WombatDB db = new WombatDB();

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeEach  /** OutResult is the method for to catch the code output.*/
    public void setUpStream() {
        System.setOut(new PrintStream(outResult));
    }

    @AfterEach  /** System.setIn() / setOut() is the method for changing the input source.*/
    public void cleanUpStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    void input(String input) { /** Input is the method for simulation that user input.*/
        input = input + "\nquit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    @DisplayName("Stage 1 Test start and Stage 4 without any command")
    void testStartingLoop() {
        input("");
        new WombatDB().commandLoop();
        String output = outResult.toString();

        assertTrue(output.contains("Starting command loop"));
        assertTrue(output.contains("No commands on line"));

    }


    @ParameterizedTest
    @DisplayName("Stage 2 Test iii. converts the input to lowercase")
    @CsvSource({"JET, jet","HELP,help","Yes,yes"})
    void testToLowercase(String userType, String sysPrint) {

        input(userType + " 1 2 3 5 May");
        new WombatDB().commandLoop();
        String output = outResult.toString();
        assertAll(
                () -> assertTrue(output.contains("Command = " + sysPrint)),
                () -> assertTrue(output.contains("1? Command = " + sysPrint)),
                () -> assertTrue(output.contains("Parameter 5 = May")),
                () -> assertTrue(output.contains("Invalid command " + sysPrint)),
                () -> assertTrue(output.contains("2? Command = quit"))
        );
    }

    @ParameterizedTest
    @DisplayName("Stage 3 commandLoop method ignore Space") //  *Number Command can be tested later
    @CsvSource({"Back  ,back", "   Front,front", "   Middle  , middle"})
    void testIgnoreSpaceAndNumberCommand(String in, String out) {
        input(in);
        new WombatDB().commandLoop();
        String output = outResult.toString();
        assertTrue(output.contains("Command = " + out));
    }

    @Test
    @DisplayName("Stage 5 Test insert and printdb")
    void testInsertAndPrintdb() {
        input("addtable Wombat\n "+
                "insert Wombat wilbur 45\n" +
                "insert Wombat joice 55 \n"+
                " printdb Wombat");
        new WombatDB().commandLoop();
        String output = outResult.toString();
        //assertEquals("",output);
        assertAll(

                () -> assertTrue(output.contains("Parameter 1 = Wombat")),
                () -> assertTrue(output.contains("Parameter 2 = wilbur")),
                () -> assertTrue(output.contains("Parameter 3 = 45")),
                () -> assertTrue(output.contains("3? Command = insert")),
                () -> assertTrue(output.contains("4? Command = printdb")),
                () -> assertTrue(output.contains("Wombat:  id = 1000, name = wilbur, length = 45cm")),
                () -> assertTrue(output.contains("Wombat:  id = 1001, name = joice, length = 55cm"))

        );

    }

    @Test
    @DisplayName("Stage 6 Test insert requirement")
    void testInsertRequirement() {
        input("addtable Wombat\n"+
                "insert Wombat joice notanint\n" + /* Test: Expecting int, found "..."  */
                "insert     Wombat    joice 54\n" + "printdb\n" + /* Test: normal but with space */
                "insert Wombat 150\n" + /* Test insufficient parameter: Invalid parameter list: insert  */
                "quit extra junk\n" +  /* Test: Invalid parameter list: printdb  */
                "printdb Wombat\n" + "quit"); /*Invalid parameter list: quit*/
        new WombatDB().commandLoop();
        String output = outResult.toString();
//        assertEquals("",output);

        assertAll(
                () -> assertTrue(output.contains("Parameter 3 = notanint")),
                () -> assertTrue(output.contains("Expecting int, found \"notanint\""), "Testing the msg. of length should be integer."),
                () -> assertTrue(output.contains("4? Command = printdb")),
                () -> assertTrue(output.contains("Invalid parameter list: printdb")),
                () -> assertTrue(output.contains("Parameter 2 = 150")),
                () -> assertTrue(output.contains("Invalid parameter list: insert")),
                () -> assertTrue(output.contains("Invalid parameter list: quit")),
                () -> assertTrue(output.contains("Wombat:  id = 1000, name = joice, length = 54cm"))
        );
    }

    @Test
    @DisplayName("Stage 6-2 Test insert requirement")
    void testInvalidTableName() {
        input("addtable Sophia\n"+
                "insert     Wombat    joice 54\n" + "printdb\n" +
                "insert Wombat 150\n" + "quit extra junk\n" + // Test insufficient parameter, should be three in Wombat
                "printdb Wombat\n" + "quit");
        new WombatDB().commandLoop();
        String output = outResult.toString();
        assertEquals("",output);

        assertAll(
                () -> assertTrue(output.contains("Parameter 3 = notanint")),
                () -> assertTrue(output.contains("Invalid table name: ... (should be of type RuntimeException)"))
        );
    }





        @Test
    @DisplayName("Test do_insert(input）command")
    void testDo_insert() {

        input(" addtable Wombat\ninsert Wombat 001 Alice");
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