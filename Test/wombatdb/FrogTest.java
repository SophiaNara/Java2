package wombatdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrogTest {
    Frog frog1, frog2, frog3;

    public String fName = "John";
    public String fColor = "Yellow";
    public int fWeight = 1;
    public int fLength = 1;

    @BeforeEach
    void setUp() {
        frog1 = new Frog(fName,fColor,fWeight,fLength);
        frog2 = new Frog("Grace", "pink", 13, 20);
    }

    @Test
    @DisplayName("Frog 1 Test toString")
    void testToString() {
        String expected = "Frog: name =" + fName +
                         ", color =" + fColor +
                            ", weight =" + fWeight +
                            "g, length = " + fLength +"cm";
        assertEquals(expected, frog1.toString());

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