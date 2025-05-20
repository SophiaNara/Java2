package wombatdb;

/**
 * A Class to represent an invalid number of arguments
 */
public class InvalidArgList extends RuntimeException {
    private final String s;

    /**
     * Create an InvalidArgList
     *
     * @param s the argument that is invalid
     */
    public InvalidArgList(String s) {
        this.s = s;
    }

    /**
     * A string representation of this Exception
     *
     * @return a message related to the exception thrown
     */
    @Override
    public String toString() {
        return "Invalid parameter list: " + s;
    }
}
