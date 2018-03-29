package ru.job4j.max;

/**
 * Class searching a max value.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Max {
    /**
     * searching a max value from arguments
     * @param first first value.
     * @param second second value.
     * @return max value from arguments.
     */
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }

    /**
     * searching a max value from 3 arguments
     * @param first first value.
     * @param second second value.
     * @param third third value.
     * @return max value from arguments.
     */
    public int max(int first, int second, int third) {
        int temp = this.max(first, second);
        temp = this.max(temp, third);
        return temp;
    }
}
