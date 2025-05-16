package stringlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringListTest {

    StringList list;
    StringList PPI;

    @BeforeEach
    void setUp() {
        list = new StringList();
        PPI = new StringList();

        PPI.add("crude oil");
        PPI.add("livestock");
        PPI.add("lumber");
        PPI.add("gasoline");
        PPI.add("cotton");
        PPI.add("clothing");
        PPI.add("cosmetics");

    }

    @ParameterizedTest
    @DisplayName("SL-1 Testing String Add and Get function.")
    @CsvSource({"Dog","Cat","Mouse","Elephant","Fish","Ant"})
    void testAddAndGet(String element) {
        list.add(element);
        assertEquals(element, list.get(0));
    }


    @Test
    @DisplayName("SL-2 Testing toString with null list.")
    void testToNullString() {
        assertEquals("List is empty: []", list.toString());
    }

    @Test
    @DisplayName("SL-3 Testing toString with list.")
    void testToString() {

        list.add("Tommy");
        list.add("Andy");
        list.add("Nancy");
        list.add("Molly");
        String Expected ="Printing List: [Tommy, Andy, Nancy, Molly]" ;
        assertEquals( Expected, list.toString());

    }

    @Test
    @DisplayName("SL-4 Testing String Grow and Size function.")
    void testGrowAndSize() {

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");

        assertEquals(6, list.size());
        assertEquals("e", list.get(4));

    }

    @Test
    @DisplayName("SL-5 Testing String contain function.")
    void testToContains() {
        assertAll(
                () -> assertTrue(PPI.contains("cotton"), "PPI should contain cotton"),
                () -> assertTrue(PPI.contains("cosmetics"), "PPI should contain cosmetics"),
                () -> assertFalse(PPI.contains("function"), "PPI should not contain function"),
                () -> assertFalse(PPI.contains("program"), "PPI should not contain function")
        );
    }

    @ParameterizedTest
    @DisplayName("SL-6 Testing searching String index function.")
    @CsvSource({"0, crude oil", "1, livestock", "2, lumber", "3, gasoline", "4, cotton", "5, clothing", "6, cosmetics"})
    void testIndexOf(int index, String element) {

        assertEquals(index, PPI.indexOf(element), "Should return " + index+ " for " + element);

    }

    @ParameterizedTest
    @DisplayName("SL-7 Testing set String into another element.")
    @CsvSource({"0, drug", "1, water", "3, milk", "6, steel"})
    void testSet(int index, String element) {
        PPI.set(index, element);
        assertEquals(index, PPI.indexOf(element),"After set, can use indexOf find out the new element position");
    }

    @ParameterizedTest
    @DisplayName("SL-8 Testing set with wrong index and throw. ")
    @CsvSource({"10, drug", "7, water", "8, milk", "20, steel"})
    void testThrowInSet(int index, String element) {

        Assertions.assertThrows( IllegalArgumentException.class, () -> PPI.set(index, element));
    // Recommendation: Should fix the code in line 182 index < size(). it not support to pass the border "7, water");
    }

    @ParameterizedTest
    @DisplayName("SL-9 Testing add with wrong index and throw. ")
    @CsvSource({"-1, drug", "8, water", "7, milk", "-8, steel"})
    void testThrowInAdd(int n, String e) {
        Assertions.assertThrows( IllegalArgumentException.class, () -> PPI.add(n, e));
        assertEquals(7, PPI.size());
        // Assertions.assertThrows( IllegalArgumentException.class, () -> PPI.add(8, "Mouse"));
    }

    @ParameterizedTest
    @DisplayName("SL-10 Testing add new element into StringList. ")
    @CsvSource({"2, drug", "4, water", "6, milk", "0, steel"})
    void testAdd(int n, String e) {
        PPI.add(n, e);
        assertEquals(e, PPI.get(n));
    }

    @ParameterizedTest
    @DisplayName("SL-11 Testing removeIndex new element into StringList. ")
    @CsvSource({"2, lumber", "4, cotton", "6, cosmetics", "0, crude oil"})
    void testRemoveIndex(int n, String e) {
        PPI.remove(n);
        assertFalse(PPI.contains(e));
        assertEquals(6, PPI.size());
//        assertTrue(PPI.contains("clothing"));
//        assertEquals("gasoline", PPI.get(2));
    }

    @ParameterizedTest
    @DisplayName("SL-12 Testing remove Index with wrong index and throw. ")
    @CsvSource({"-1", "8", "7", "30"})
    void testThrowInRemoveIndex(int n) {

        Assertions.assertThrows( IllegalArgumentException.class, () -> PPI.remove(n));

    }

    @ParameterizedTest
    @DisplayName("SL-13 Testing remove Element from StringList.")
    @CsvSource({"lumber", "cotton", "cosmetics", "crude oil"})
    void testRemove(String e) {
        PPI.remove(e);
        assertEquals(6, PPI.size());
        assertFalse(PPI.contains(e));
        assertTrue(!PPI.contains(e));
    }

    @ParameterizedTest
    @DisplayName("SL-14 Testing remove the item not existed in StringList should return false.")
    @CsvSource({"Fish", "Mouse", "Jacket", "Sun"})
    void testRemoveNoInList(String e) {
        assertFalse(PPI.remove(e)); // remove sth. is !contains(element), will return false.
    }

    @Test
    @DisplayName("SL-15 Testing subList in normal range.")
    void testSubList() {
        PPI = PPI.subList(1,6);
        String exp = "Printing List: [livestock, lumber, gasoline, cotton, clothing]";
        assertEquals(exp, PPI.toString());
        PPI = PPI.subList(2,3);
        String exp2 = "Printing List: [gasoline]";
        assertEquals(exp2, PPI.toString());
    }

    @Test
    @DisplayName("SL-15a Testing subList in normal range to choose last element.")
    // how can subList to the end of element?
    void testSubListToBoarder() {
        PPI = PPI.subList(1,6);
        String exp = "Printing List: [livestock, lumber, gasoline, cotton, clothing]";
        assertEquals(exp, PPI.toString());
        PPI = PPI.subList(3,5);
        String exp2 = "Printing List: [cotton, clothing]";
        assertEquals(exp2, PPI.toString(), "It supposes to include clothing");
    }

    @Test
    @DisplayName("SL-16 Testing subList in scenario fromIndex == toIndex.")
    void testSubListToEqualFrom() {
        PPI = PPI.subList(6,6);
        assertEquals(0, PPI.size());
    }

    @ParameterizedTest
    @DisplayName("SL-17 Testing subList in scenario: fromIndex > toIndex.")
    @CsvSource({"4,3","6,5","7,1"})
    void testSubListFromBiggerThanTo(int from, int to) {
//        Assertions.assertThrows( IllegalArgumentException.class, () -> PPI.subList(from,to));
        Exception ex = assertThrows( IllegalArgumentException.class, () -> PPI.subList(from,to));
        assertTrue(ex.getMessage().contains("Indices out of order"));
    }

    @ParameterizedTest
    @DisplayName("SL-18 Testing subList in scenario: Invalid index.")
    @CsvSource({"-5,-3","0,9","-1,18"})
    void testSubListInvalid(int from, int to) {
//        Assertions.assertThrows( IllegalArgumentException.class, () -> PPI.subList(from,to));
        Exception ex = assertThrows( IllegalArgumentException.class, () -> PPI.subList(from,to));
//        assertTrue(ex.getMessage().contains("Invalid index"));
        assertEquals("Invalid index", ex.getMessage());

    }

    @Test
    @DisplayName("SL-19 Testing removeRange in normal scenario")
    void removeRange() {

        PPI.removeRange(0,2);
        String exp = "Printing List: [lumber, gasoline, cotton, clothing, cosmetics]";
        assertEquals(exp, PPI.toString());

    }

    @Test
    @DisplayName("SL-19 Testing removeRange in normal scenario")
    void lastIndexOf() {
    }

    @org.junit.jupiter.api.Test
    void clear() {
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
    }

    @org.junit.jupiter.api.Test
    void toArray() {
    }

    @org.junit.jupiter.api.Test
    void removeIf() {
    }
}