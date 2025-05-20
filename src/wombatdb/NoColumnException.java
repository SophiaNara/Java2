package wombatdb;

/**
 * Exception to deal with a request for column information and the index is invalid
 */
public class NoColumnException extends RuntimeException {
    /**
     * No such column exists
     *
     * @param index the index of the invalid column
     */
    public NoColumnException(int index) {
        super("Invalid column index: " + index);
    }
}
