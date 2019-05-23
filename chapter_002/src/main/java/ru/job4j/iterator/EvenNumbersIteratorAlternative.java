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
public class EvenNumbersIteratorAlternative implements Iterator {
    /** values storage */
    private final int[] numbers;

    /** iterator cursor */
    private int cursor = -1;

    /** number of next even element in the storage.
     * If it does not exist then it`s nextEven = -1.
     * By default nextEven = null.
     */
    private Integer nextEven = null;

    /**
     * Constructor
     * @param numbers - specified numbers array.
     */
    public EvenNumbersIteratorAlternative(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * The method checks presence of the even elements in the storage after cursor.
     * @return - true - if in the storage after cursor still there are even elements,
     *           false - if there are no even elements after cursor in the storage.
     */
    @Override
    public boolean hasNext() {
        this.nextEven = this.findNextEvenPosition();
        return this.nextEven != -1;
    }

    /**
     * The method returns next even element in the storage and increments cursor.
     * @throws NoSuchElementException if there are no more even elements in the storage after cursor.
     * @return next even element in the storage.
     */
    @Override
    public Integer next() {
        if (nextEven == null || (nextEven >= 0 && nextEven <= cursor)) {
            nextEven = this.findNextEvenPosition();
            if (nextEven == -1) {
                throw new NoSuchElementException();
            }
        } else if (nextEven == -1) {
            throw new NoSuchElementException();
        }
        cursor = nextEven;
        return numbers[cursor];
    }

    /**
     * The method is looking for a next even element in the storage.
     * @return position of next even element in the storage.
     *         If next even element was not found the method returns -1.
     */
    private Integer findNextEvenPosition() {
        Integer result = -1;
        for (int i = cursor + 1; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
