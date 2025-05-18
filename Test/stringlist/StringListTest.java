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
    StringList material;

    @BeforeEach
    void setUp() {
        list = new StringList();
        PPI = new StringList();
        material = new StringList();

        PPI.add("crude oil");
        PPI.add("livestock");
        PPI.add("lumber");
        PPI.add("gasoline");
        PPI.add("cotton");
        PPI.add("clothing");
        PPI.add("cosmetics");

        material.add("crude oil");
        material.add("livestock");
        material.add("lumber");

    }

    @ParameterizedTest
    @DisplayName("SL-1 Testing String Add and Get function.")
    @CsvSource({"Dog","Cat","Mouse","Elephant","Fish","Ant"})
    void testAddAndGet(String element) {
        list.add(element);
        assertEquals(element, list.get(0));

    }

    @ParameterizedTest
    @DisplayName("SL-1-2 Testing Get String with invalid index and throws IllegalArgumentException.")
    @CsvSource({"-1","7","8","10","20","30"})
    void testGetInvalid(int n) {

        Assertions.assertThrows( IllegalArgumentException.class, () -> PPI.get(n));

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

        assertEquals(0, list.size());
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        assertAll(
                () -> assertEquals(6, list.size()),
                () ->assertEquals("f", list.get(5)),
                () ->assertEquals("e", list.get(4))
        );

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
    @DisplayName("SL-20 Testing removeRange in toIndex == fromIndex")
    void removeRangeToEqualsFrom() {

        PPI.removeRange(2,2);
        String exp = "Printing List: [crude oil, livestock, lumber, gasoline, cotton, clothing, cosmetics]";
        assertEquals(exp, PPI.toString());
//        PPI.removeRange(0,2);
//        exp = "Printing List: [lumber, gasoline, cotton, clothing, cosmetics]";
//        assertEquals(exp, PPI.toString());
//        PPI.removeRange(3,4);
//        exp = "Printing List: [lumber, gasoline, cotton, cosmetics]";
//        assertEquals(exp, PPI.toString());

    }

    @ParameterizedTest
    @DisplayName("SL-21 Testing removeRange in toIndex < fromIndex / Indices out of order")
    @CsvSource({"5,2","4,1","7,0"})
    void removeRangeToLessFrom(int f, int t) {

        Exception ex = assertThrows( IllegalArgumentException.class, () -> PPI.removeRange(f,t));
        assertTrue(ex.getMessage().contains("Indices out of order"));
    }

    @ParameterizedTest
    @DisplayName("SL-22 Testing removeRange in scenario, out of boundary: Invalid index")
    @CsvSource({"7,9","-1,1","-8,-2"})
    void removeRangeInvalid(int from, int to) {

        Exception ex = assertThrows( IllegalArgumentException.class, () -> PPI.subList(from,to));
        assertAll(
                () -> assertTrue(ex.getMessage().contains("Invalid index")),
                () ->assertEquals("Invalid index", ex.getMessage())
        );

    }

    @ParameterizedTest
    @DisplayName("SL-23 Testing lastIndexOf in normal scenario")
    @CsvSource({"2, lumber", "4, cotton", "6, cosmetics", "7, crude oil"})
    void testLastIndexOf(int index, String e) {

        PPI.add("crude oil");
        assertEquals(index, PPI.lastIndexOf(e));

    }

    @ParameterizedTest
    @DisplayName("SL-24 Testing lastIndexOf does not contain the element with Start/EndsWithFilter")
    @CsvSource({"cotton", "lumber", "clothing", "cosmetics"})
    void testLastIndexOfNotFound( String e) {

        EndsWithFilter endsWithEr = new EndsWithFilter(PPI, "er");
        EndsWithFilter endsWithIng = new EndsWithFilter(PPI, "ing");
        StartsWithFilter startsWithCo = new StartsWithFilter(PPI, "co");
        PPI.removeIf(endsWithEr);
        PPI.removeIf(endsWithIng);
        PPI.removeIf(startsWithCo);
        assertEquals(-1, PPI.lastIndexOf(e));

    }



    @Test
    @DisplayName("SL-25 Testing clear and isEmpty")
    void testClearAndIsEmpty() {

        assertFalse(PPI.isEmpty());
        PPI.clear();

        assertAll(
                () -> assertEquals(0, PPI.size()),
                () -> assertFalse(PPI.contains("cotton")),
                () -> assertTrue(PPI.isEmpty())
        );

    }


    @Test
    @DisplayName("SL-26 Testing two list whether contain the same elements in the same order")
    void testEquals() {

        assertFalse(PPI.equals(material));
        PPI = PPI.subList(0,3);
        assertTrue(PPI.equals(material));
        PPI.clear();
        //assertEquals("", PPI.toString());
        StringList testList = new StringList();
        assertAll(
                () -> assertEquals(false, material.equals(PPI)),
        /** sl == this */
                () -> assertTrue(PPI.equals(PPI), "sl == this should return true"),
        /** sl == null */
                () -> assertTrue(testList.isEmpty(), "check whether sl is empty"),
                () -> assertFalse(list.equals(testList), "sl == null should return false")
        );



    }

    @Test
    @DisplayName("SL-27 Testing method convert list to an array ")
    void toArray() {

        String[]list = PPI.toArray();
        assertAll(
                () -> assertEquals(PPI.size(), list.length),
                () -> assertEquals("crude oil", list[0]),
                () -> assertEquals(PPI.get(3), list[3]),
                () -> assertEquals(PPI.get(6), list[6])
        );

    }

    @Test
    @DisplayName("SL-28 Testing removeIf with ContainsFilter")
    void removeIf() {

        ContainsFilter containsFilterOT = new ContainsFilter(PPI,"ot");
        PPI.removeIf(containsFilterOT);
        assertFalse(PPI.contains("cotton"));

    }

}