package wombatdb;

import java.awt.*;

/**
 * Class that represents a LocationData
 */
public class LocationData implements DBComponent {
    private final Point loc;
    private final String name;

    /**
     * Default constructor: used to get an instance for use with insert and select clauses
     */
    public LocationData() {
        name = "";
        loc = null;
    }

    /**
     * Create a new LocationData
     *
     * @param name the name at this location
     * @param x    the x coordinate of this location
     * @param y    the y coordinate of this location
     */
    public LocationData(String name, String x, String y) {
        this.name = name;
        try {
            loc = new Point(Integer.parseInt(x), Integer.parseInt(y));
        } catch (NumberFormatException nfe) {
            throw new InvalidArgList("insert");
        }
    }

    /**
     * Get the number of columns in the LocationData 'table'
     *
     * @return the number of columns
     */
    @Override
    public int getNumberOfColumns() {
        return 3;
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
            case 2 -> Integer.toString(loc.x);
            case 3 -> Integer.toString(loc.y);
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
            case 2 -> "loc.x";
            case 3 -> "loc.y";
            default -> throw new NoColumnException(index);
        };
    }

    /**
     * Get a string representation of the structure of the LocationData table
     *
     * @return a string that describes the structure
     */
    public String getStructure() {
        return " --------------\n" +
                "| name | x | y |\n" +
                " --------------";
    }

    /**
     * Get a string representation of this LocationData
     *
     * @return the representation of this LocationData
     */
    @Override
    public String toString() {
        return name + " is at Location " + loc;
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
//    public int compare(Object o1, Object o2) {
//        return 0;
//    }

    public int compare(Object o1, Object o2) {

        if (o1 == null || o2 == null) {
            throw new NullPointerException("Arguments must not be null");
        }

        if (!(o1 instanceof LocationData) || !(o2 instanceof LocationData)) {
            throw new ClassCastException("Objects must be of type LocationData");
        }

        LocationData l1 = (LocationData) o1;
        LocationData l2 = (LocationData) o2;

        return l1.name.compareTo(l2.name);
    }

}
