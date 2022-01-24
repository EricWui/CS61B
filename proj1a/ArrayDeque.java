public class ArrayDeque<T> {
    private T[] elements;
    private int startIndex;
    private int endIndex;

    public ArrayDeque() {
        elements = (T[]) new Object[8];
        startIndex = 3;
        endIndex = 4;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */

    public void addFirst(T item) {
        resize();
        elements[startIndex--] = item;
    }

    /**
     * Adds an item of type T to the back of the deque
     */

    public void addLast(T item) {
        resize();
        elements[endIndex++] = item;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */

    public boolean isEmpty() {
        return (startIndex + 1) == endIndex;
    }

    /**
     * Returns the number of items in the deque
     */

    public int size() {
        return endIndex - startIndex - 1;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */

    public void printDeque() {
        for (int i = startIndex + 1; i < endIndex; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */

    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        return elements[++startIndex];
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */

    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        return elements[--endIndex];
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */

    public T get(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            return null;
        }
        return elements[startIndex + index + 1];
    }

    /**
     * when factor is very low or very high,
     * build a new array and copy the elements to the new array.
     */

    private void resize() {
        int length = elements.length;
        int size = size();
        double factor = size / (length + 0.0);
        int newLength;
        if (factor < 0.25 && length > 16) {
            //make size of elements small
            T[] newElements = (T[]) new Object[length / 2];
            cpElements(newElements);
            elements = newElements;
        }
        if (factor > 0.75) {
            //make size of elements large
            T[] newElements = (T[]) new Object[length * 2];
            cpElements(newElements);
            elements = newElements;
        }
        if (startIndex <= 0 || endIndex >= elements.length - 1) {
            //when only addFirst or addLast
            T[] newElements = (T[]) new Object[length];
            cpElements(newElements);
            elements = newElements;
        }
    }

    /**
     * build a new array and copy the elements to the new array.
     */

    private void cpElements(T[] newElements) {
        int length = newElements.length;
        int index = startIndex + 1;
        int size = size();
        startIndex = (length - size()) / 2 - 1;
        for (int i = 0; i < size; ++i) {
            newElements[startIndex + i + 1] = elements[index++];
        }
        endIndex = startIndex + size + 1;
    }
}
