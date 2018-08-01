import java.util.Arrays;
import java.util.Comparator;

/**
 * Resizable-array implementation of the List interface.
 */
public class ArrayList<T> implements List<T> {

    private static final int MINIMUM_SIZE = 1024;

    private int size = 0;
    private T[] array = (T[]) new Object[MINIMUM_SIZE];


    @Override
    public boolean add(T value) {
        return add(size, value);
    }

    /**
     * Add value to list at index.
     *
     * @param index to add value.
     * @param value to add to list.
     */
    public boolean add(int index, T value) {
        if (size >= array.length)
            grow();
        if (index == size) {
            array[size++] = value;
        } else {
            // Shift the array down one spot
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
        }
        return true;
    }

    @Override
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            T obj = array[i];
            if (obj.equals(value)) {
                if (remove(i) != null) return true;
                return false;
            }
        }
        return false;
    }

    /**
     * Remove value at index from list.
     *
     * @param index of value to remove.
     * @return value at index.
     */
    public T remove(int index) {
        if (index < 0 || index >= size) return null;

        T t = array[index];
        if (index != --size) {
            // Shift the array down one spot
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        array[size] = null;

        int shrinkSize = array.length >> 1;
        if (shrinkSize >= MINIMUM_SIZE && size < shrinkSize)
            shrink();

        return t;
    }

    // Grow the array by 50%
    private void grow() {
        int growSize = size + (size << 1);
        array = Arrays.copyOf(array, growSize);
    }

    // Shrink the array by 50%
    private void shrink() {
        int shrinkSize = array.length >> 1;
        array = Arrays.copyOf(array, shrinkSize);
    }

    /**
     * Set value at index.
     *
     * @param index of value to set.
     * @param value to set.
     * @return value previously at index.
     */
    public T set(int index, T value) {
        if (index < 0 || index >= size) return null;
        T t = array[index];
        array[index] = value;
        return t;
    }

    /**
     * Get value at index.
     *
     * @param index of value to get.
     * @return value at index.
     */
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return array[index];
    }

    @Override
    public void clear() {
        size = 0;
    }


    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            T obj = array[i];
            if (obj.equals(value)) return true;
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            T obj = array[i];
            if (obj.equals(value)) return i;
        }
        return -1;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean validate() {
        int localSize = 0;
        for (int i = 0; i < array.length; i++) {
            T t = array[i];
            if (i < size) {
                if (t == null) return false;
                localSize++;
            } else {
                if (t != null) return false;
            }
        }
        return (localSize == size);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort(array,0,size,c);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(array[i]).append(", ");
        }
        return builder.toString();
    }
}

