package wombatdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LocationDataTest {

    LocationData loc1;
    LocationData loc2;
    LocationData loc3;


    public String locName1 = "Bedford campus";
    public String x1 = "123";
    public String y1 = "456";
    @BeforeEach
    void setUp() {
        loc1 = new LocationData(locName1, x1, y1);
        loc2 = new LocationData("Sturt", "234","567");
        loc3 = new LocationData("Bedford campus", "123","456");
    }
        // loc1 = new LocationData("Bedford campus", "123", "456");

    @Test
    @DisplayName("Location 0 Test constructor with a invalid x or y")
    void testInvalidConstructor() {

        assertThrows(InvalidArgList.class, () -> {
            LocationData loc4 = new LocationData("Adelaide", "wq", "2");
        });

    }


    @Test
    @DisplayName("Location 1 Test toString")
    void testToString() {

        String expected = locName1 +" is at Location java.awt.Point[x="+ x1 +",y=" + y1 + "]";
        assertEquals(expected, loc1.toString());

    }

    @Test
    @DisplayName("Location 2 Test number of columns")
    void testGetNumberOfColumns() {
        assertEquals(3,loc1.getNumberOfColumns());
        assertEquals(3,loc2.getNumberOfColumns());
    }

    @Test
    @DisplayName("Location 3 Test Get a particular column value with an index")
    void testGetColumn() {
        assertAll(
                () -> assertEquals(locName1, loc1.getColumn(1)),
                () -> assertEquals(x1, loc1.getColumn(2)),
                () -> assertEquals(y1, loc1.getColumn(3))
        );
    }

    @Test
    @DisplayName("Location 3-2 Test Get a particular column value with an invalid index")
    void testGetInvalidColumn() {
        assertThrows(NoColumnException.class, () -> loc1.getColumn(4));
        assertThrows(NoColumnException.class, () -> loc1.getColumn(-1));
    }


    @Test
    @DisplayName("Location 4 Test Get a particular column name with an index")
    void testGetColumnName() {
        assertAll(
                () -> assertEquals("name", loc1.getColumnName(1)),
                () -> assertEquals("loc.x", loc1.getColumnName(2)),
                () -> assertEquals("loc.y", loc1.getColumnName(3))
        );
    }

    @Test
    @DisplayName("Location 4-2 Test Get a particular column value with an invalid invalid index")
    void testGetInvalidColumnName() {
        assertThrows(NoColumnException.class, () -> loc1.getColumnName(4));
        assertThrows(NoColumnException.class, () -> loc1.getColumnName(0));
    }

    @Test
    @DisplayName("Location 5 Get a string representation of the structure of the table")
    void getStructure() {
        assertEquals(" --------------\n" +
                "| name | x | y |\n" +
                " --------------", loc2.getStructure());
    }

//    loc2 = new LocationData("Sturt", "234","567");
//    loc3 = new LocationData("Bedford campus", "123","456");
//    loc1 = new LocationData("Bedford campus", "123", "456");


    @Test
    @DisplayName("Location 6-1 Test Compare expected negative integer")
    void testCompare() {
        int actual = loc1.compare(loc1, loc2);
        // System.out.println(actual);
        assertTrue(actual < 0);
    }

    @Test
    @DisplayName("Location 6-2 Test Compare expected positive integer")
    void testComparePositive() {
        int actual2 = loc1.compare(loc2, loc1);
        assertFalse(actual2 < 0);
    }

    @Test
    @DisplayName("Location 6-3 Test Compare expected return zero")
    void testCompareZero() {
        LocationData locZero = new LocationData("Bedford campus", "123", "456");
        assertEquals(0, loc1.compare(loc1, locZero));
    }

    @Test
    @DisplayName("Location 6-4 Test an argument is null")
    void testCompareWithNull() {

        assertThrows( NullPointerException.class ,() -> loc1.compare(loc1, null));
        assertThrows( NullPointerException.class, () -> loc1.compare(null, loc1));

    }

    @Test
    @DisplayName("Location 6-5 Test an argument is in ClassCastException")
    void testCompareClassCastException() {
        LocationData loc5 = new LocationData();
        Wombat wombat = new Wombat("Fred", "15");
        Frog f = new Frog("Name", "color","11","10");
        assertThrows(ClassCastException.class, () -> loc1.compare(wombat, loc1));
        assertThrows(ClassCastException.class, () -> loc5.compare(f, loc5));

    }

}