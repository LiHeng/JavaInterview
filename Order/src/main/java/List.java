import java.util.Comparator;

/**
 * A list or sequence is an abstract data type that implements an ordered
 * collection of values, where the same value may occur more than once.
 */
public interface List<T> {

    /**
     * Add value to list.
     * 
     * @param value to add.
     * @return True if added.
     */
    public boolean add(T value);

    /**
     * Remove value from list.
     * 
     * @param value to remove.
     * @return True if removed.
     */
    public boolean remove(T value);

    /**
     * Clear the entire list.
     */
    public void clear();

    /**
     * Does the list contain value.
     * 
     * @param value to search list for.
     * @return True if list contains value.
     */
    public boolean contains(T value);

    public int indexOf(T value);

    /**
     * Size of the list.
     * 
     * @return size of the list.
     */
    public int size();

    /**
     * Get value at index.
     *
     * @param index of value to get.
     * @return value at index.
     */
    public T get(int index);

    /**
     * Validate the list according to the invariants.
     * 
     * @return True if the list is valid.
     */
    public boolean validate();

    /**
     * Sorts this list according to the order induced by the specified Comparator.
     * @param c the Comparator used to compare list elements.
     */
    public void sort(Comparator<? super T> c);

}
