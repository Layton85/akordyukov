package ru.job4j.loop;

/**
 * Class Factorial.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * The method calculates the factorial from the input parameter
     * @param n input parameter
     * @return the factorial from the input parameter
     */
    public int calc(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
