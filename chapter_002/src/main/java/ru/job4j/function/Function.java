package ru.job4j.function;

import java.util.*;

/**
 * Function - the class representing use of functional interfaces.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Function {
    /**
     * The method provides calculating specified function in some diapason.
     * @param start - start value.
     * @param end - end value (the first value which don`t calculating.
     * @param func - specified function.
     * @return - The List of calculated values.
     */
    List<Double> diapason(int start, int end, java.util.function.Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
