package wombatdb;

/**
 * Class that represents a Frog
 */
public class Frog implements DBComponent {
    private final String name;
    private final String color;
    private final int weight;
    private final int length;

    /**
     * Default constructor: used to get an instance for use with insert and select clauses
     */
    public Frog(String name, String color, int weight, int length) {
        this.name = "";
        this.color = "";
        this.weight = 0;
        this.length = 0;
    }

    /**
     * Create a new Frog
     *
     * @param name   the name of this Frog
     * @param color  the color of this Frog
     * @param weight the weight of this Frog
     * @param length the length of this Frog
     */
    public Frog(String name, String color, String weight, String length) {
        this.name = name;
        this.color = color;
        this.weight = Integer.parseInt(weight);
        this.length = Integer.parseInt(length);
    }

    /**
     * Get the number of columns in the Frog 'table'
     *
     * @return the number of columns
     */
    @Override
    public int getNumberOfColumns() {
        return 4;
    }

    /**
     * Get a particular column value based on an index
     *
     * @param index the column value to get
     * @return the value stored at the index
     * @throws NoColumnException if the index is invalid
     */
    @Override
    public String getColumn(int index) {
        return switch (index) {
            case 1 -> name;
            case 2 -> color;
            case 3 -> Integer.toString(weight);
            case 4 -> Integer.toString(length);
            default -> throw new NoColumnException(index);
        };
    }

    /**
     * Get a particular column name based on an index
     *
     * @param index the column name to get
     * @return the name associated with the index
     * @throws NoColumnException if the index is invalid
     */
    @Override
    public String getColumnName(int index) {
        return switch (index) {
            case 1 -> "name";
            case 2 -> "color";
            case 3 -> "weight";
            case 4 -> "length";
            default -> throw new NoColumnException(index);
        };
    }

    /**
     * Get a string representation of the structure of the Frog table
     *
     * @return a string that describes the structure
     */
    public String getStructure() {
        return " --------------------------------\n" +
                "| name | color | weight | length |\n" +
                " --------------------------------";
    }

    /**
     * Get a string representation of this Frog
     *
     * @return the representation of this Frog
     */
    @Override
    public String toString() {

        String result =
                "Frog: " +
                "name = " + name + ", " +
                "color = " + color + ", " +
                "weight = " + weight + "g, " +
                "length = " + length + "cm";


        return result;
    }

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
