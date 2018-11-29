package ru.job4j.list;

import java.util.List;

/**
 * ConvertList2Array - class converts List into the two-dimensional Array.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {
    /**
     * The method formes two-dimensional array from the List.
     * If the List is empty or the number of rows is zero the method returns [0][0] array.
     * If the list size less then number of elements of the two-dimensional array the remained elements are filled with zero.
     * @param list - input List
     * @param rows - number of rows
     * @return - two-dimensional array formed from the List.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] array;
        if (!list.isEmpty() && rows > 0) {
            int cells = list.size() / rows;
            if (list.size() % rows > 0) {
                cells++;
            }
            array = new int[rows][cells];
            int i = 0;
            int j = 0;
            for (Integer el : list) {
                array[i][j] = el;
                if (j < (cells - 1)) {
                    j++;
                } else {
                    j = 0;
                    i++;
                }
            }
        } else {
            array = new int[0][0];
        }
        return array;
    }
}