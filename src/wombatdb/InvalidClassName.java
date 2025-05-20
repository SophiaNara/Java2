package wombatdb;

/**
 * A Class to represent an invalid class name
 */
public class InvalidClassName extends RuntimeException {
    private final String s;

    /**
     * Create an InvalidClassName
     *
     * @param s the class name that is invalid
     */
    public InvalidClassName(String s) {
        this.s = s;
    }

    /**
     * A string representation of this Exception
     *
     * @return a message related to the exception thrown
     */
    @Override
    public String toString() {
        return "Invalid Class name: " + s;
    }
}
