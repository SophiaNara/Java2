package wombatdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LocationDataTest {

//    建構子是否正確建立物件
//    getNumberOfColumns() 是否回傳 3
//    getColumn() 是否正確回傳欄位值
//    getColumnName() 是否正確回傳欄位名稱
//    toString() 是否正確格式化輸出
//    錯誤處理（例如：傳入無效的欄位索引）
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
    void getColumnName() {
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


    @ParameterizedTest
    @DisplayName("Location 6-1 Test Compare expected negative integer")
    @CsvSource({"1,-17","2,-1","3,-1"})
    void compare(int index, int result) {

        assertEquals(result, loc1.getColumn(index).compareTo(loc2.getColumn(index)));

    }

    @ParameterizedTest
    @DisplayName("Location 6-2 Test Compare expected positive integer")
    @CsvSource({"1,17","2,1","3,1"})
    void testComparePositive(int index, int result) {
//        System.out.println(
//                loc2.getColumn(1).compareTo(loc1.getColumn(1))
//        );
//
//        System.out.println(
//                loc1.getColumn(2).compareTo(loc2.getColumn(2))
//        );

        assertEquals(result, loc2.getColumn(index).compareTo(loc1.getColumn(index)));

    }

    @ParameterizedTest
    @DisplayName("Location 6-3 Test Compare expected return zero")
    @CsvSource({"1","2","3"})
    void testCompareZero(int index) {

        assertEquals(0, loc1.getColumn(index).compareTo(loc3.getColumn(index)));

    }

    @Test
    @DisplayName("Location 6-4 Test an argument is null")
    void testCompareWithNull() {

        LocationData locEmpty = new LocationData(null, "123","789");
        assertThrows( NullPointerException.class ,() -> loc1.getColumn(1).compareTo(null));
        assertThrows( NullPointerException.class, () -> locEmpty.getColumn(1).compareTo(loc1.getColumn(1)));

    }

    @Test
    @DisplayName("Location 6-5 Test an argument is in ClassCastException")
    void testCompareClassCastException() {

        Wombat wombat = new Wombat("Fred", 15);

        LocationData comparator = new LocationData();
        assertThrows(ClassCastException.class, () -> comparator.compare(wombat, loc1));

//        assertThrows( ClassCastException.class ,() -> loc1.getColumn(1).compareTo(wombat.getColumn(1)));
//        assertThrows( ClassCastException.class, () -> wombat.getColumn(1).compareTo(loc1.getColumn(1)));

    }

}