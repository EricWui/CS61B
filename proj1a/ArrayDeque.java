public class ArrayDeque<T> {
    private T[] elements;
    private int startIndex;
    private int endIndex;

    public ArrayDeque() {
        elements = (T[]) new Object[8];
        startIndex = 0;
        endIndex = elements.length;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */

    public void addFirst(T item) {
        resize();
        elements[startIndex++] = item;
    }

    /**
     * Adds an item of type T to the back of the deque
     */

    public void addLast(T item) {
        resize();
        elements[--endIndex] = item;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */

    public boolean isEmpty() {
        return endIndex - startIndex == elements.length;
    }

    /**
     * Returns the number of items in the deque
     */

    public int size() {
        return startIndex - 0 + elements.length - endIndex;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */

    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
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
        T item = elements[--startIndex];
        resize();
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */

    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        T item = elements[endIndex++];
        resize();
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */

    public T get(int index) {
        if (index >= size()) {
            return null;
        }
        int addFirstLength = startIndex;
        if (index < addFirstLength) {
            return elements[addFirstLength - index - 1];
        }
        return elements[elements.length - (index - addFirstLength) - 1];
    }

    /**
     * when factor is very low or very high,
     * build a new array and copy the elements to the new array.
     */

    private void resize() {
        int length = elements.length;
        int size = size();
        double factor = size / (length + 0.0);
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
    }

    /**
     * build a new array and copy the elements to the new array.
     */

    private void cpElements(T[] newElements) {
        for (int i = 0; i < startIndex; ++i) {
            newElements[i] = elements[i];
        }
        int newIndex = newElements.length;
        for (int i = elements.length - 1; i >= endIndex; --i) {
            newElements[--newIndex] = elements[i];
        }
        endIndex = newIndex;
    }
}
