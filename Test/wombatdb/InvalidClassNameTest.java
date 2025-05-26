package wombatdb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class InvalidClassNameTest {

    @ParameterizedTest
    @DisplayName("(Test InvalidClassName Construction and toString)")
    @CsvSource({"Dog", "Cat", "Biliby"})
    void testConstructor(String s) {
        InvalidClassName msg = new InvalidClassName(s);
        String expectedResult = "Invalid Class name: " + s;
        assertEquals(expectedResult, msg.toString());

    }

}