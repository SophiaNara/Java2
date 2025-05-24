package wombatdb;

/**
 * Class that represents a Wombat
 */
public class Wombat implements DBComponent {
    static int next_id = 1000;
    private final int id;
    String name;
    int length;

    /**
     * Default constructor: used to get an instance for use with insert and select clauses
     */
    public Wombat() {
        name = "";
        length = 0;
        id = -1;
    }

    public int getId() {
        return id;
    }

    /**
     * Create a new Wombat
     *
     * @param name   the name of the Wombat
     * @param length the length of the Wombat
     */
    public Wombat(String name, int length) {
        this.name = name;
        this.length = length;
        id = next_id++;
    }

    /**
     * Get the number of columns in the Wombat 'table'
     *
     * @return the number of columns
     */
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
    public String getColumn(int index) {
        return switch (index) {
            case 1 -> Integer.toString(id);
            case 2 -> name;
            case 3 -> Integer.toString(length);
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
    public String getColumnName(int index) {
        return switch (index) {
            case 1 -> "Id";
            case 2 -> "Name";
            case 3 -> "Length";
            default -> throw new NoColumnException(index);
        };
    }

    /**
     * Get a string representation of the structure of the Wombat table
     *
     * @return a string that describes the structure
     */
    public String getStructure() {
        return " --------------------\n" +
                "| id | name | length |\n" +
                " --------------------";
    }

    /**
     * Get a string representation of this Wombat
     *
     * @return the representation of this Wombat
     */
    @Override
    public String toString() {
        return "Wombat:  id = " + id + ", name = " + name +
                ", length = " + length + "cm";
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

//        public int compare(Object o1, Object o2) {
//           if(o1 == null || o2 == null) {
//               throw new NullPointerException("Arguments must not be full");
//           }
//
//           if(!(o1 instanceof Wombat) || !(o2 instanceof Wombat)) {
//               throw new ClassCastException("ar must be of type of frog");
//           }
//
//           Wombat f1 = (Wombat) o1;
//        Wombat f2 = (Wombat) o2;
//
//           return Integer.compare(
//                   Integer.parseInt(f1.getColumn(1)),
//                   Integer.parseInt(f2.getColumn(1))
//           );
//    }

}
