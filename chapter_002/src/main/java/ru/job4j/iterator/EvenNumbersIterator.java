package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EvenNumbersIterator - class implements interface Iterator
 * and specializes in work with even numbers.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class EvenNumbersIterator implements Iterator {
    /** values storage */
    private final int[] numbers;

    /** iterator cursor */
    private int cursor = 0;

    /**
     * Constructor
     * @param numbers - specified numbers array.
     */
    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * The method checks presence of the even elements in the storage after cursor.
     * Besides the method moves cursor to the next even position.
     * Second invocation without calling next() does not performs to the cursor incrementation.
     * @return - true - if in the storage after cursor still there are even elements,
     *           false - if there are no even elements after cursor in the storage.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = cursor; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                cursor = i;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * The method returns next even element in the storage and increments cursor.
     * @throws NoSuchElementException if there are no more even elements in the storage after cursor.
     * @return next even element in the storage.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.numbers[cursor++];
    }
}