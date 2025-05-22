package wombatdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class WombatTest {
    static Wombat w1;
    static Wombat w20;
    static Wombat w30;
    static Wombat w40;

    @BeforeEach
    void setUp() {
         w1 = new Wombat();
         w20 = new Wombat("Zoe", 4);
         w30 = new Wombat("Sherry", 20);
         w40 = new Wombat("Mercy", 30);

    }

    @Test
    @DisplayName("W1 Test wombat get column content.")
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
    @DisplayName("W2 Test wombat get column content out of index range.")
    void testGetInvalidColumn() {

        Exception ex = assertThrows( wombatdb.NoColumnException.class, () -> w20.getColumn(4));
        assertTrue(ex.getMessage().contains("Invalid column index: 4"));
//        assertEquals("Invalid column index:",ex);
    }

//    public String toString() {
//        return "Wombat:  id = " + id + ", name = " + name +
//                ", length = " + length + "cm";
//    }

    @ParameterizedTest
    @DisplayName("W3 Test wombat get column content out of index range.")
    @CsvSource({"1000, Zoe, 4, w20", "1003, Mercy, 30, w30"})

    void testToString(String id, String name, String length, Wombat wb) {
        String Expected ="Wombat:  id = "+ id +", name = " + name + ", length = " + length + "cm" ;
//        Wombat wb = wb
        assertEquals( Expected, wb.toString());

    }



    @Test
    void thenComparing() {

    }

    @Test
    void testThenComparing() {
    }

    @Test
    void testThenComparing1() {
    }

    @Test
    void thenComparingInt() {
    }

    @Test
    void thenComparingLong() {
    }

    @Test
    void thenComparingDouble() {
    }

    @Test
    void reverseOrder() {
    }

    @Test
    void naturalOrder() {
    }

    @Test
    void nullsFirst() {
    }

    @Test
    void nullsLast() {
    }

    @Test
    void comparing() {
    }

    @Test
    void testComparing() {
    }

    @Test
    void comparingInt() {
    }

    @Test
    void comparingLong() {
    }

    @Test
    void comparingDouble() {
    }

    @Test
    void getNumberOfColumns() {
    }

    @Test
    void getColumn() {
    }

    @Test
    void getColumnName() {
    }

    @Test
    void getStructure() {
    }


    @Test
    void compare() {
    }
}