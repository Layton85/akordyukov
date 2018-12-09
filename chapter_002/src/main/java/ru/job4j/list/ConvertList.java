package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ConverList - class converts list of arrays into the List of values.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList {
    /**
     * The method converts the list of int arrays int the list of Integer values.
     * @param list - list of int arrays
     * @return - list of Integer values
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> rlist = new ArrayList<>();
        for (int[] ar : list) {
            for (int el : ar) {
                rlist.add(el);
            }
        }
        return rlist;
    }
}
