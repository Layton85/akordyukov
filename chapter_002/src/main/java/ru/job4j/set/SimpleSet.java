package ru.job4j.set;

import ru.job4j.list.DynamicArray;

import java.util.*;

/**
 * SimpleSet<E> - class realizes the set on the array.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleSet<E> implements Iterable<E> {
    /** minimum data container size */
    private static final int MIN_SIZE = 5;

    /** number of elements in the data container */
    private int size = 0;

    /** Data container */
    private DynamicArray<E> elementData = new DynamicArray<>(MIN_SIZE);

    /**
     * Add method.
     * @param value - value for addition.
     */
    public void add(E value) {
        boolean result = true;
        for (E element : this.elementData) {
            if ((element != null && element.equals(value)) || (element == null && value == null)) {
                result = false;
                break;
            }
        }
        if (result) {
            this.elementData.add(value);
            this.size++;
        }
    }

    /**
     * Returns an iterator over SimpleSet elements of type {@code E}.
     * @throws NoSuchElementException - if there are no elements in the data container which correspond
     * to value of the cursor.
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;
            @Override
            public boolean hasNext() {
                return this.cursor < size;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return elementData.get(this.cursor++);
            }
        };
    }
}
