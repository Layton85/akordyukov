package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DynamicLinkedList<E> - class describes dynamic container based on the single linked list.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DynamicLinkedList<E> implements Iterable<E> {
    /** Current size of container */
    private int size;

    /** First node of the container */
    private Node<E> first;

    /** Structural modifications counter. */
    private int modCount = 0;

    /**
     * Add-method.
     * @param value - new <E> value for addition.
     */
    public void add(E value) {
        Node<E> newLink = new Node<>(value);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        this.modCount++;
    }

    /**
     * Get-method.
     * @param index - position for getting value.
     * @throws IndexOutOfBoundsException - if index is out of array`s positions which were filled in.
     * @return - The value on this position.
     */
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Node<E> - inner class which describes the elements of container.
     * @author Alexander Kordyukov (alex-programm@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private static class Node<E> {
        /** Data. */
        E data;

        /** The link on the next node. */
        Node<E> next;

        /**
         * Constructor.
         * @param data - data.
         */
        Node(E data) {
            this.data = data;
        }
    }

    /**
     * Override method which returns an iterator over DynamicLinkedList elements of type {@code E}.
     * @throws ConcurrentModificationException - if DynamicLinkedList was structurally modified
     * after iterator creation.
     * @throws NoSuchElementException - if there are no elements in the DynamicLinkedList which corresponds
     * to value of the cursor.
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor;
            private Node<E> current = first;
            private final int expectedModeCount = modCount;
            @Override
            public boolean hasNext() {
                if (this.expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return this.cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (cursor > 0) {
                    current = current.next;
                }
                cursor++;
                return current.data;
            }
        };
    }
}
