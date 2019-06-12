package ru.job4j.list;

import java.util.*;

/**
 * DynamicArray<E> - class describes dynamic array.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DynamicArray<E> implements Iterable<E> {
    /** Container - array of Objects. */
    private Object[] container;

    /** Structural modifications counter. */
    private int modeCount = 0;

    /** current position for addition new element */
    private int position = 0;

    /**
     * Constructor.
     * @param size - The initial size of the array.
     */
    public DynamicArray(int size) {
        this.container = new Object[size];
    }

    /**
     * Add method.
     * @param value - value for addition.
     * @return - boolean value:
     *              true - if the element was successfully added,
     *              false - if the element was not added.
     */
    public boolean add(E value) {
        if (this.position == this.container.length) {
            this.container = this.grow(this.container.length + 1);
            this.modeCount++;
        }
        this.container[this.position++] = value;
        return true;
    }

    /**
     * Get method.
     * @param index - position for getting value.
     * @throws IndexOutOfBoundsException - if index is out of array`s positions which were filled in.
     * @return - The value on this position.
     */
    public E get(int index) {
        Objects.checkIndex(index, this.position);
        return (E) this.container[index];
    }

    /**
     * Helper method which generate new size array.
     * @param minCapacity - minimum new size of the array.
     * @return - new elements container.
     */
    private Object[] grow(int minCapacity) {
        int newLength = minCapacity * 2;
        return Arrays.copyOf(this.container, newLength);
    }

    /**
     * Returns an iterator over DynamicArray elements of type {@code E}.
     * @throws ConcurrentModificationException - if DynamicArray was structurally modified after iterator creation.
     * @throws NoSuchElementException - if there are no elements in the DynamicArray which correspond
     * to value of the cursor.
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor;
            private final int expectedModeCount = modeCount;
            @Override
            public boolean hasNext() {
                if (this.expectedModeCount != modeCount) {
                    throw new ConcurrentModificationException();
                }
                return this.cursor < position;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[this.cursor++];
            }
        };
    }
}