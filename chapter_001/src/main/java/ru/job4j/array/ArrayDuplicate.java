package ru.job4j.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * ArrayDuplicate - class allows to delete the duplicated strings.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * The method allows to delete the duplicated strings from the received array of strings.
     * @param array of strings.
     * @return array of strings without duplicated members.
     */
    public String[] remove(String[] array) {
        int cnt = 0;
        for (int i = 0; i < array.length - cnt; i++) {
            for (int j = i + 1; j < array.length - cnt; j++) {
                if (array[j].equals(array[i])) {
                    String temp = array[array.length - cnt - 1];
                    array[array.length - cnt - 1] = array[j];
                    array[j] = temp;
                    j--;
                    cnt++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - cnt);
    }
}
