package ru.job4j.array;

/**
 * Turn - class for reversing an array
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     * The method reversing input array
     * @param array
     * @return reversing array
     */
    public int[] turn(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[array.length - 1 - i];
            array[array.length - 1 - i] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
