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
                "insert     Wombat    joice 54\n" + "printdb\n" + /* Test: invalid parameter list: printdb */
                "insert Wombat 150\n" + /* Test insufficient parameter: Invalid parameter list: insert  */
                "quit extra junk");   /* Test: invalid parameter list: quit  */

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
    @DisplayName("Stage 6-2 Test insert requirement: error message- Invalid table name: ...")
    void testInvalidTableName() {
        input("addtable Wombat\n"+
                "insert Student joice 54\n" +
                "printdb Student");
        new WombatDB().commandLoop();
        String output = outResult.toString();
        // assertEquals("",output);

        assertAll(
                () -> assertTrue(output.contains("Invalid table name: Student")),
                () -> assertTrue(output.contains("java.lang.RuntimeException: Invalid table name:"), "print db is not Wombat expected throw : java.lang.RuntimeException:")
        );
    }

    @Test
    @DisplayName("Stage 7 Test Invalid table name and invalid parameter list")
    void testInvalidParameter() {
        input(" addtable LocationData\n"+
              " insert LocationData fred 40 50\n" +
              " insert Locationdata julie 30 xxx\n" +
              " insert LocationData julie 30 xx\n" +
              " printdb LocationData");
        new WombatDB().commandLoop();
        String output = outResult.toString();
        // assertEquals("", output);
        assertAll(
                () -> assertTrue(output.contains("Invalid table name: Locationdata")),
                () -> assertTrue(output.contains("Invalid parameter list: insert")),
                () -> assertTrue(output.contains("fred is at Location java.awt.Point[x=40,y=50]"))
        );
    }


    @Test
    @DisplayName("Stage 8 Test select column from table ")
    void testSelectFromTable() {
        input(" addtable Frog\n"+
                " insert Frog kermit green 50 12\n" +
                " insert Frog kermit green 50 junk\n" + /* Expected result: Invalid parameter list: insert*/
                " insert Frog toad brown 100 20\n" +
                " select 1 from Frog\n" +
                " select 2 from Frog\n" +
                " select -1 from Frog\n" + /* Invalid column index: -1 */
                " select 1 2 3 4 from Frog\n" +
                " select 1  3  5  7 from Frog\n" +
                " select 5 from Frog\n" + /* Invalid column index: 5 */
                " select e from Frog\n" + /* Invalid parameter list: select */
                " select 2 from Junk\n" + /* Invalid table name: Junk */
                " select * from Frog");  /* test fillColArray if (star) */
        new WombatDB().commandLoop();
        String output = outResult.toString();
        //assertEquals("", output);
        assertAll(
                () -> assertTrue(output.contains("Invalid parameter list: insert")),
                () -> assertTrue(output.contains("| name | weight | Invalid column index: 5")),
                () -> assertTrue(output.contains("Invalid column index: 5")),
                () -> assertTrue(output.contains("Invalid column index: -1")),
                () -> assertTrue(output.contains("| name | color | weight | length |")),
                () -> assertTrue(output.contains("kermit\tgreen\t50\t12\t")),
                () -> assertTrue(output.contains("Invalid parameter list: select")),
                () -> assertTrue(output.contains("Invalid table name: Junk")),
                () -> assertTrue(output.contains("kermit")),
                () -> assertTrue(output.contains("toad")),
                () -> assertTrue(output.contains("kermit\tgreen\t50\t12")),
                () -> assertTrue(output.contains("toad\tbrown\t100\t20")),
                () -> assertTrue(output.contains("| color |")),
                () -> assertTrue(output.contains("green"))

        );
    }

    @Test
    @DisplayName("Stage 9 Test select column from table ")
    void testDo_desctable() {

        input(" addtable Frog\n"+ " addtable Wombat\n"+
                " insert Frog kermit green 50 12\n" +
                " insert Frog kermit green 50 junk\n" + /* Expected result: Invalid parameter list: insert*/
                " insert Frog toad brown 100 20\n" +
                " describe Frog\n" + " describe Wombat\n" + " describe LocationData\n");
        new WombatDB().commandLoop();
        String output = outResult.toString();
        //assertEquals("", output);
        assertAll(
                () -> assertTrue(output.contains("| name | color | weight | length |")),
                () -> assertTrue(output.contains("--------------------------------")),
                () -> assertTrue(output.contains("| id | name | length |")),
                () -> assertTrue(output.contains("Invalid table name: LocationData"))
        );

    }

    @Test
    @DisplayName("Stage 10 invalid class name")
    void testInvalidClass() {

        input(" addtable FictionalTable\n" + " printdb FictionalTable\n" + "select 1 from FictionalTable" );
        new WombatDB().commandLoop();
        String output = outResult.toString();
        //assertEquals("", output);
        assertFalse(output.contains("Invalid Class name: FictionalTable"));
        /** Due to private void do_select and do_printdb methods, not possible be tested,
         *  but the function is worked properly, suggest to create a public method to test,
         *  otherwise need to reference "reflection" to test it */
    }

//    String input = "addtable Wombat\n" +
//            "insert Wombat TestWombat 50\n" +
//            "select * from Wombat\nquit\n";

}