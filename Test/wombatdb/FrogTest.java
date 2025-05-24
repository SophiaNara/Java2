package wombatdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FrogTest {
    Frog frog1, frog2, frog3;

    public String fName = "John";
    public String fColor = "Yellow";
    public int fWeight = 1;
    public int fLength = 1;
    public String fWeight3 = "11";
    public String fLength3 = "14";

    @BeforeEach
    void setUp() {
        frog1 = new Frog(fName,fColor,fWeight,fLength);
        frog2 = new Frog("Grace", "pink", "13", "10");
        frog3 = new Frog(fName,fColor,fWeight3,fLength3);
    }

    @Test
    @DisplayName("Frog 1 Test Default constructor and toString (weight and length is int)")
    void testFirstConstructorString() {
        // System.out.println(frog2.toString());
        String expected = "Frog: name = " + fName +
                         ", color = " + fColor +
                            ", weight = " + fWeight +
                            "g, length = " + fLength +"cm";
        assertEquals(expected, frog1.toString());

    }

    @Test
    @DisplayName("Frog 1-2 Test second constructor and toString")
    void testSecondConstructorString() {
        String expected = "Frog: name = Grace, color = pink, weight = 13g, length = 10cm";
        assertEquals(expected, frog2.toString());
    }

    @Test
    @DisplayName("Frog 2 Test number of columns")
    void testGetNumberOfColumns() {
        assertEquals(4, frog2.getNumberOfColumns());
        assertEquals(4, frog1.getNumberOfColumns());
    }

    @Test
    @DisplayName("Frog 3 Test Get a particular column value with an index")
    void testGetColumn() {
        assertAll(
                () -> assertEquals(fName, frog3.getColumn(1)),
                () -> assertEquals(fColor, frog3.getColumn(2)),
                () -> assertEquals(fWeight3, frog3.getColumn(3)),
                () -> assertEquals(fLength3, frog3.getColumn(4))
                );
    }

    @Test
    @DisplayName("Frog 3-2 Test Get a particular column value with an invalid index")
    void testGetInvalidColumn() {
        assertThrows(NoColumnException.class, () -> frog3.getColumn(5));
        assertThrows(NoColumnException.class, () -> frog3.getColumn(-1));
    }


    @Test
    @DisplayName("Frog 4 Test Get a particular column name with an index")
    void getColumnName() {
        assertAll(
                () -> assertEquals("name", frog3.getColumnName(1)),
                () -> assertEquals("color", frog1.getColumnName(2)),
                () -> assertEquals("weight", frog2.getColumnName(3)),
                () -> assertEquals("length", frog2.getColumnName(4))

        );
    }

    @Test
    @DisplayName("Frog 4-2 Test Get a particular column value with an invalid invalid index")
    void testGetInvalidColumnName() {
        assertThrows(NoColumnException.class, () -> frog1.getColumnName(5));
        assertThrows(NoColumnException.class, () -> frog2.getColumnName(0));
    }

    @Test
    @DisplayName("Frog 5 Get a string representation of the structure of the table")
    void getStructure() {
        assertEquals(" --------------------------------\n" +
                "| name | color | weight | length |\n" +
                " --------------------------------", frog1.getStructure());
    }

    // for reference   frog2 = new Frog("Grace", "pink", "13", "10");
    //    frog3 = new Frog("John","Yellow","11","14");
    @Test
    @DisplayName("Frog 6-1 Test Compare expected negative and Positive integer（compare column3 weight）")
    void testCompareNegative() {
        Frog f1 = new Frog("Sweet","Yellow","14","14");
        int result = f1.compare(frog3, f1);
        int result2 = f1.compare(f1, frog3);
        // System.out.println(result);
        assertTrue(result < 0);
        assertFalse(result2 < 0);
    }

    @Test
    @DisplayName("Frog 6-2 Test Compare expected return zero")
    void testCompareZero() {
        Frog f1 = new Frog("Sweet","Yellow","11","14");
        int result = f1.compare(frog3, f1);
        // System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Frog 6-3 Test an argument is null")
    void testCompareWithNull() {

        assertThrows( NullPointerException.class ,() -> frog1.compare(frog1, null));
        assertThrows( NullPointerException.class ,() -> frog2.compare(null, frog2));
    }

    @Test
    @DisplayName("Frog 6-4 Test an argument is in ClassCastException")
    void testCompareClassCastException() {

        Wombat wombat = new Wombat("Fred", 15);
        assertThrows(ClassCastException.class, () -> frog1.compare(wombat, frog2));

    }



}