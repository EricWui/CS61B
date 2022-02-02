public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque
     */
    @Override
    public void addFirst(T item) {
        Node node = new Node(item, null, null);
        node.prev = sentinel;
        if (size == 0) {
            //deque is null.
            node.next = sentinel;
            sentinel.prev = node;
        } else {
            node.next = sentinel.next;
            sentinel.next.prev = node;
        }
        sentinel.next = node;
        ++size;
    }

    /**
     * Adds an item of type T to the back of the deque
     */
    @Override
    public void addLast(T item) {
        Node node = new Node(item, null, null);
        node.next = sentinel;
        if (size == 0) {
            //deque is null.
            node.prev = sentinel;
            sentinel.next = node;
        } else {
            node.prev = sentinel.prev;
            sentinel.prev.next = node;
        }
        sentinel.prev = node;
        ++size;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        Node ptr = sentinel.next;
        while (size-- > 0) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = sentinel.next;
        if (size == 1) {
            sentinel.next = null;
            sentinel.prev = null;
        } else {
            sentinel.next = first.next;
            first.next.prev = sentinel;
        }
        --size;
        return first.item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = sentinel.prev;
        if (size == 1) {
            sentinel.prev = null;
            sentinel.next = null;
        } else {
            sentinel.prev = last.prev;
            last.prev.next = sentinel;
        }
        --size;
        return last.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        }
        Node ptr = sentinel.next;
        while (index-- > 0) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    /**
     * Helper method of getRecursive.
     */
    private T getRecursiveHelper(Node ptr, int index) {
        if (index < 0) {
            return null;
        }
        if (index == 0) {
            return ptr.item;
        }
        return getRecursiveHelper(ptr.next, index - 1);
    }


    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
