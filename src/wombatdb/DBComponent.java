package wombatdb;

import java.util.Comparator;

public interface DBComponent extends Comparator {
    /**
     * Get the number of columns for a DBComponent
     *
     * @return the number of columns
     */
    int getNumberOfColumns();

    /**
     * Get a column value based on a given index
     *
     * @param index the index for which the column value is required
     * @return the value stored at the column at the index
     * @throws NoColumnException if the index is invalid
     */
    String getColumn(int index);

    /**
     * Get a column name based on a given index
     *
     * @param index the index for which the column name is required
     * @return the name of the column at the index
     * @throws NoColumnException if the index is invalid
     */
    String getColumnName(int index);

    /**
     * Get a represention of the DBComponent. Similar to the 'describe' SQL function
     *
     * @return a representation of the DBComponent
     */
    String getStructure();
}
