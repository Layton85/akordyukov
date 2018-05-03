package ru.job4j.array;

/**
 * BubbleSort - class sorting an array using bubble sort method.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {
    /**
     * The method sorting an array using bubble sort method.
     * @param array
     * @return sorting array from less to greater
     */
    public int[] sort(int[] array) {
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - 1 - j; i++) {
                if (array[i + 1] < array[i]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
        return array;
    }
}
