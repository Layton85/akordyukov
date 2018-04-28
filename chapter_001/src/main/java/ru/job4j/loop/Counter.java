package ru.job4j.loop;

/**
 * Class Counter.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    /**
     * the method calculates the sum of even numbers in the range from start to finish
     * @param start first value.
     * @param finish second value.
     * @return the sum of even numbers in the range from start to finish.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int cnt = start; cnt <= finish; cnt++) {
            if (cnt % 2 == 0) {
                sum += cnt;
            }
        }
        return sum;
    }
}
