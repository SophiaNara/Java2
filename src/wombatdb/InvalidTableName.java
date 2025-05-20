package wombatdb;

/**
 * A Class to represent an invalid number of arguments
 */
public class InvalidTableName extends RuntimeException {
    private final String s;

    /**
     * Create an InvalidTableName
     *
     * @param s the argument that is invalid
     */
    public InvalidTableName(String s) {
        this.s = s;
    }

    /**
     * A string representation of this Exception
     *
     * @return a message related to the exception thrown
     */
    @Override
    public String toString() {
        return "Invalid table name: " + s;
    }
}
