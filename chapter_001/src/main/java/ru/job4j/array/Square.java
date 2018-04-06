package ru.job4j.array;

/**
 * Square
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Square {
    /**
     * The method outputs array of squares of indices of its elements,
     * where the indication is from 1 to the number of elements in the array
     * @param bound number of elements in array
     * @return array of squares of indices of its elements
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i=0; i!=bound; i++) {
            rst[i] = (int)Math.pow(i+1, 2);
        }
        return rst;
    }
}