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

    @ParameterizedTest
    @DisplayName("Frog 6-1 Test Compare expected negative and positive integer")
    @CsvSource({"1,3","2,-23","3,-2","4,4"})
    void compare(int index, int result) {

        assertEquals(result, frog3.getColumn(index).compareTo(frog2.getColumn(index)));

    }

    @ParameterizedTest
    @DisplayName("Location 6-3 Test Compare expected return zero")
    @CsvSource({"1","2","3", "4"})
    void testCompareZero(int index) {
        Frog frog4 = new Frog("Grace", "pink", "13", "10");
        assertEquals(0, frog2.getColumn(index).compareTo(frog4.getColumn(index)));

    }

    @Test
    @DisplayName("Frog 6-4 Test an argument is null")
    void testCompareWithNull() {
        Frog frog5 = new Frog(null, "pink", "13", "10");
        assertThrows( NullPointerException.class ,() -> frog1.getColumn(1).compareTo(null));
        assertThrows( NullPointerException.class, () -> frog5.getColumn(1).compareTo(frog1.getColumn(1)));

    }

    @Test
    @DisplayName("Location 6-5 Test an argument is in ClassCastException")
    void testCompareClassCastException() {

        Wombat wombat = new Wombat("Fred", 15);

        LocationData comparator = new LocationData();
        assertThrows(ClassCastException.class, () -> comparator.compare(wombat, frog1));

//        assertThrows( ClassCastException.class ,() -> loc1.getColumn(1).compareTo(wombat.getColumn(1)));
//        assertThrows( ClassCastException.class, () -> wombat.getColumn(1).compareTo(loc1.getColumn(1)));

    }

}