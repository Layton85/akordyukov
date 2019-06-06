package ru.job4j.generic;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Iterator;

/**
 * SimpleArray - class-wrapper for the array of Objects.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {
    /** array of Objects. */
    Object[] elementData;

    /** current position for adding new element */
    int position = 0;

    /** array`s size */
    int size = 0;

    /**
     * Constructor
     * @param size - array`s size.
     */
    public SimpleArray(int size) {
        this.elementData = new Object[size];
        this.size = size;
    }

    /**
     * Add method.
     * @param model - adding value.
     * @return - boolean value:
     *              true - if the element was successfully added,
     *              false - if the element was not added (because of the array is full).
     */
    public boolean add(T model) {
        boolean result = false;
        if (this.position < this.size) {
            this.elementData[this.position++] = model;
            result = true;
        }
        return result;
    }

    /**
     * Set method
     * @param index - position for setting new value.
     * @param model - new value.
     * @throws IndexOutOfBoundsException - if index is out of array`s positions which were filled in.
     * @return - The old value on this position.
     */
    public T set(int index, T model) {
        Objects.checkIndex(index, this.position);
        T oldValue = (T) this.elementData[index];
        this.elementData[index] = model;
        return oldValue;
    }

    /**
     * Remove method.
     * After this operation the value on the current position is null.
     * @param index - position for removing value.
     * @throws IndexOutOfBoundsException - if index is out of array`s positions which were filled in.
     * @return - The old value on this position.
     */
    public T remove(int index) {
        Objects.checkIndex(index, this.position);
        T oldValue = (T) this.elementData[index];
        this.elementData[index] = null;
        return oldValue;
    }

    /**
     * Get method.
     * @param index - position for getting value.
     * @throws IndexOutOfBoundsException - if index is out of array`s positions which were filled in.
     * @return - The value on this position.
     */
    public T get(int index) {
        Objects.checkIndex(index, this.position);
        return (T) this.elementData[index];
    }

    /**
     * Override method Iterator().
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor;
            @Override
            public boolean hasNext() {
               return this.cursor < position;
            }

            @Override
            public T next() {
                if (cursor >= position) {
                    throw new NoSuchElementException();
                }
                return (T) elementData[this.cursor++];
            }
        };
    }
}
