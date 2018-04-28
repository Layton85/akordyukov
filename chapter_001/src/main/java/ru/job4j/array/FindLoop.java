package ru.job4j.array;

/**
 * FindLoop
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FindLoop {
    /**
     * The method searches the array for a given value of the element by simply enumerating
     * @param data array
     * @param el value of the element that is searched in the array
     * @return index of the found element
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index != data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
