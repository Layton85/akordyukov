package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter - class provides converting iterator of iterators into
 * simple iterator (like flatting operation).
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Converter {
    /**
     * The method provides converting iterator of iterators into
     * simple iterator (like flatting operation).
     * @param it - iterator of iterators of Integer elements.
     * @return - "flatted" iterator of Integer elements.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> itNested = new ArrayList<Integer>().iterator();
            @Override
            public boolean hasNext() {
                boolean result = false;
                while (it.hasNext() && !itNested.hasNext()) {
                    itNested = it.next();
                }
                result = itNested.hasNext();
                return result;
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return itNested.next();
            }
        };
    }
}