package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ConvertMatrix2List - class converts two-dimensional Array into the List.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2List {
    /**
     * The method formes the List from two-dimensional array.
     * If the array is empty the method returns empty List.
     * @param array - two-dimensional array of int values.
     * @return - List of Integer values.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] rows : array) {
            for (int value : rows) {
                list.add(value);
            }
        }
        return list;
    }
}
