package wombatdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class WombatTest {
    public Wombat w1;
    public Wombat w20;
    public Wombat w30;
    public Wombat w40;

    @BeforeEach
    void setUp() {
        Wombat.next_id = 1000;
        w1 = new Wombat();
        w20 = new Wombat("Zoe", 4);
        w30 = new Wombat("Sherry", 20);
        w40 = new Wombat("Mercy", 30);
    }

    @Test
    @DisplayName("W 0 Test Default constructor and toString")
    void testFirstConstructorString() {
         System.out.println(w1.toString());
         String expected = "Wombat:  id = -1, name = , length = 0cm";
         assertEquals(expected, w1.toString());
    }

    @Test
    @DisplayName("W 1 Test new wombat constructor and toString.")
    void testToString() {

        String Expected ="Wombat:  id = 1000, name = Zoe, length = 4cm" ;
        Wombat w3 = new Wombat("Faith", 5);
        String Expected2 ="Wombat:  id = 1003, name = Faith, length = 5cm" ;
        assertAll(
                () -> assertEquals(Expected, w20.toString()),
                () -> assertEquals(Expected2, w3.toString())
        );


    }

    @Test
    @DisplayName("W 2 Test number of columns")
    void testGetNumberOfColumns() {
        assertEquals(3, w1.getNumberOfColumns());
        assertEquals(3, w20.getNumberOfColumns());
    }


    @Test
    @DisplayName("W 3 Test wombat get column content.")
    void testGetColumn() {
        assertAll(
                () -> assertEquals(3, w20.getNumberOfColumns()),
                () -> assertEquals("1000", w20.getColumn(1)),
                () -> assertEquals("Zoe", w20.getColumn(2)),
                () -> assertEquals("4", w20.getColumn(3)),
                () -> assertEquals("1001", w30.getColumn(1)),
                () -> assertEquals("Sherry", w30.getColumn(2))
        );
    }

    @Test
    @DisplayName("W 3-2 Test Get a particular column value with an invalid index")
    void testGetInvalidColumn() {
        assertThrows(NoColumnException.class, () -> w1.getColumn(4));
        assertThrows(NoColumnException.class, () -> w30.getColumn(0));
    }

    @Test
    @DisplayName("W 4 Test Get a particular column name with an index")
    void getColumnName() {
        assertAll(
                () -> assertEquals("Id", w20.getColumnName(1)),
                () -> assertEquals("Name", w1.getColumnName(2)),
                () -> assertEquals("Length", w30.getColumnName(3))
        );
    }

    @Test
    @DisplayName("W 4-2 Test Get a particular column value with an invalid invalid index")
    void testGetInvalidColumnName() {
        assertThrows(NoColumnException.class, () -> w20.getColumnName(4));
        assertThrows(NoColumnException.class, () -> w30.getColumnName(0));
    }

    @Test
    @DisplayName("W 5 Get a string representation of the structure of the table")
    void getStructure() {
        assertEquals(" --------------------\n" +
                "| id | name | length |\n" +
                " --------------------", w1.getStructure());
    }

//    w20 = id = 1000, name = Zoe, length = 4cm
//    w30 = id = 1001, name = Sherry, length = 20cm
//    w40 = id = 1002, name = Mercy, length = 30cm
    // w1 = id = -1, name = , length = 0cm
    @Test
    @DisplayName("W 6-1 Test Compare expected negative and Positive integer（compare column3 weight）")
    void testCompareNegative() {
        int result = w1.compare(w20, w30);
        int result2 = w1.compare(w30, w40);
        assertTrue(result < 0);
        assertFalse(result2 > 0);
    }

//            System.out.println(w1.toString());
//         System.out.println(result);
//        System.out.println(result2);

    @Test
    @DisplayName("W 6-2 Test Compare expected return zero")
    void testCompareZero() {
        Wombat wombat1 = new Wombat();
        // System.out.println(wombat1.toString());
        int result = w1.compare(wombat1, w1);
        // System.out.println(result);
        assertEquals(0, result);
    }
    /** This method is use default constructor, because hypothesise compare ID,
     * it is not possible same id in here)
    */

    @Test
    @DisplayName("W 6-3 Test an argument is null")
    void testCompareWithNull() {

        assertThrows( NullPointerException.class ,() -> w20.compare(w20, null));
        assertThrows( NullPointerException.class ,() -> w30.compare(null, w30));
    }

    @Test
    @DisplayName("W 6-5 Test an argument is in ClassCastException")
    void testCompareClassCastException() {

        Frog frog = new Frog("Fred", "Blue", 11, 10);
        assertThrows(ClassCastException.class, () -> w20.compare(w20, frog));

    }

}
