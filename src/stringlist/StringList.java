package stringlist;

import java.util.function.Supplier;

/**
 * Class that simulates a list for storing Strings.
 *
 * @author Carl Mooney
 * @version 1.0.11
 */
public class StringList {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 4;

    /**
     * The amount to grow this {@code StringList} when {@code grow()} is called.
     *
     * @see #grow()
     */
    private static final int GROW = 5;

    /**
     * The array buffer into which the elements of the {@code StringList} are stored.
     */
    private String[] elementData;

    /**
     * The size of the {@code StringList} (the number of elements it contains).
     */
    private int size = 0;

    /**
     * Constructs an empty list with an initial capacity of four.
     */
    public StringList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     */
    public StringList(int initialCapacity) {
        elementData = new String[initialCapacity];
    }

    /**
     * Appends the specified element to the end of this list. If the list is
     * full then {@code grow()} should be called, before inserting the element.
     *
     * @param element the element to be appended to this list
     * @return {@code true} if successful
     * @see #grow()
     */
    public boolean add(String element) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = element;
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IllegalArgumentException the message "Invalid index: "
     * {@code index)}, if the index is invalid
     */

    public String get(int index) {
        if (index >= 0 && index <= size && index < elementData.length) {
            return elementData[index];
        }
        throw new IllegalArgumentException("Invalid index: " + index);
    }

    /**
     * Returns a string representation of this list. The string representation
     * consists of a list of the list's elements, enclosed in square brackets
     * "[]".  <br>
     * Adjacent elements are separated by the characters ", " (comma and space).
     *
     * @return a {@code String} representation of this list
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "List is empty: []";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Printing List: [");
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                sb.append(elementData[i]).append(", ");
            } else {
                sb.append(elementData[i]).append("]");
            }
        }
        return sb.toString();
    }

    /**
     * Increases the capacity of the current list by {@code GROW}.
     *
     * @return an array that has {@code GROW} more slots than the current array
     * @see #GROW
     */
    public String[] grow() {
        String[] temp = new String[size + GROW];
        int i = 0;
        if (size >= 0) {
            for (String element : elementData) {
                temp[i++] = element;
            }
        }
        return temp;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Checks to see if the given element is in this list.
     *
     * @param element the element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element,
     * {@code false} otherwise
     */
    public boolean contains(String element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param element the element to find
     * @return the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(String element) {
        int indexOfFoundValue = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                indexOfFoundValue = i;
                i = size;
            }
        }
        return indexOfFoundValue;
    }

    /**
     * Replaces the element at the specified position in this list
     * with the specified element.
     *
     * @param index   the index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IllegalArgumentException with the message "Invalid index: "
     * {@code index)}, if index is invalid.
     */
    public String set(int index, String element) {
        String previousElement;
        if (index >= 0 && index <= size() && index < elementData.length) {
            previousElement = get(index);
            elementData[index] = element;
            return previousElement;
        }
        throw new IllegalArgumentException("Invalid index: " + index);
    }
    // should fix the code  in line 182. if (index >= 0 && index < size())

    /**
     * Inserts the specified element at the specified index in this list.
     * If the list is full then {@code grow()} should be called, before
     * inserting the element.<br>
     * Shifts the element currently at that index (if any) and any subsequent
     * elements to the right (adds one to their indices).<br>
     * If index is invalid throw an IllegalArgumentException with the message
     * "Invalid index: " {@code index)}
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IllegalArgumentException with the message "Invalid index: "
     * {@code index)}, if the index is invalid
     * @see #grow()
     */
    public void add(int index, String element) {
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        if (size == elementData.length) {
            elementData = grow();
        }

        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        set(index, element);
        size++;

    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IllegalArgumentException with the message "Invalid index: "
     * {@code index)}, if the index is invalid
     */
    public String remove(int index) {
        String element;
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        } else {
            element = get(index);
            for (int i = index; i < size - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            size--;
        }
        return element;
    }

    /**
     * Removes the first occurrence of the specified element from this list
     * if it is present.  If the list does not contain the element, it is
     * unchanged. <br>
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param element the element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element,
     * {@code false} otherwise
     */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        } else {
            int indexOfFoundElement = indexOf(element);
            for (int i = indexOfFoundElement; i < size - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            size--;
            return true;
        }
    }

    /**
     * Returns a {@code StringList} of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.<br>
     * If {@code fromIndex} and {@code toIndex} are equal, the returned list is empty.
     *
     * @param fromIndex the starting index (inclusive) for the sublist
     * @param toIndex   the ending index (exclusive) for the sublist
     * @return a {@code StringList} of the portion of this list between the two indices
     * @throws IllegalArgumentException with the message
     *                                  "Indices out of order", if {@code toIndex} is less
     *                                  than {@code fromIndex}
     * @throws IllegalArgumentException with the message
     *                                  "Invalid index", if either of the indices is invalid
     */
    public StringList subList(int fromIndex, int toIndex) {
        if (toIndex < fromIndex) {
            throw new IllegalArgumentException("Indices out of order");
        }
        if (toIndex < 0 || toIndex > size - 1 || fromIndex < 0
                || fromIndex > size - 1) {
            throw new IllegalArgumentException("Invalid index");
        }
        StringList temp = new StringList(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            temp.add(elementData[i]);
        }
        return temp;
    }


    /**
     * Removes from this list all the elements whose index is between
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive. <br>
     * Shifts any succeeding elements to the left (reduces their index).
     * This call shortens the list by ({@code toIndex} - {@code fromIndex}) elements.<br>
     * If {@code toIndex} equals {@code fromIndex}, this operation has no effect.
     *
     * @param fromIndex the starting index (inclusive) for the removal
     * @param toIndex   the ending index (exclusive) for the removal
     * @throws IllegalArgumentException with the message
     *                                  "Indices out of order", if {@code toIndex} is less
     *                                  than {@code fromIndex}
     * @throws IllegalArgumentException with the message
     *                                  "Invalid index", if either of the indices is invalid
     */
    public void removeRange(int fromIndex, int toIndex) {
        if (toIndex == fromIndex) {
            return;
        }
        if (toIndex < fromIndex) {
            throw new IllegalArgumentException("Indices out of order");
        } else if (toIndex < 0 || toIndex > size - 1 || fromIndex < 0
                || fromIndex > size - 1) {
            throw new IllegalArgumentException("Invalid index");
        } else {
            for (int i = 0; i < (toIndex - fromIndex); i++) {
                remove(fromIndex);
            }
        }
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param element the element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(String element) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes all the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Compares the specified {@code StringList} with this list for equality.
     * Returns {@code true} if and only if the specified {@code StringList}
     * is also a list, <br>both lists have the same size, and all corresponding
     * pairs of elements in the two lists are equal.<br>
     * In other words, two lists are defined to be equal if they contain the
     * same elements in the same order.
     *
     * @param sl the {@code StringList} to be compared for equality with this list
     * @return {@code true} if the specified {@code StringList} is equal to this list
     */
    public boolean equals(StringList sl) {
        if (sl == this) {
            return true;
        }
        if (sl == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(elementData[i].equals(sl.get(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an array containing all the elements in this list in the proper
     * sequence (from first to last element).
     *
     * @return an array containing all the elements in this list
     * in the proper sequence
     */
    public String[] toArray() {
        String[] arr = new String[size];
        System.arraycopy(elementData, 0, arr, 0, size);
        return arr;
    }

    /*
     *
     * Removes all the elements of this list that satisfy the given filter.
     *
     * @param filter a filter which determines elements to be removed
     * @return {@code true} if any elements were removed
     */
    public boolean removeIf(Filter filter) {
        boolean removed = false;
        int[] elements = filter.test();
        if (elements.length != 0) {
            removed = true;
            for (int index = elements.length - 1; index >= 0; index--) {
                remove(elements[index]);
            }
        }
        return removed;
    }
}

