package ru.job4j.matrix;

import java.util.*;

/**
 * MatrixIterator - generic class implements interface Iterator
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MatrixIterator<T> implements Iterator<T> {
    /**Matrix of some values*/
    private final T[][] values;

    /** row counter **/
    private Integer row = 0;
    /** column counter */
    private Integer column = -1;

    /**
     * Constructor
     * @param values - specified matrix
     */
    public MatrixIterator(T[][] values) {
        this.values = values;
    }

    /**
     * The method checks presence of the elements in the matrix.
     * @return - true - if in the matrix still there are elements,
     *           false - if there are no elements in the matrix.
     */
    public boolean hasNext() {
        return !(this.column.equals(this.values[this.row].length - 1)
                && this.row.equals(this.values.length - 1));
    }

    /**
     * The method returns next element in the matrix
     * @throws NoSuchElementException if there are no more elements in matrix
     * @return next element in the matrix
     */
    public T next() {
        Optional<T> result = Optional.empty();
        if (this.column.equals(this.values[this.row].length - 1)) {
            if (this.row.equals(this.values.length - 1)) {
                throw new NoSuchElementException();
            } else {
                this.column = 0;
                result = Optional.ofNullable(this.values[++this.row][this.column]);
            }
        } else {
            result = Optional.ofNullable(this.values[this.row][++this.column]);
        }
        return result.orElse(null);
    }
}
