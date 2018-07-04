package ru.job4j.array;

/**
 * SortedArrayFormation - class formate sorted on increase array from 2 sorted on increase integer arrays.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortedArrayFormation {
    /**
     * int array.
     */
    private int[] ar;

    /**
     * Constructor.
     * @param a - first sorted initializing array.
     * @param b - second sorted initializing array.
     */
    public SortedArrayFormation(int[] a, int[] b) {
        this.ar = this.fillSortedArray(a, b);
    }

    /**
     * Get-method for sorted array
     * @return ar - resulting sorted array.
     */
    public int[] getAr() {
        return ar;
    }

    /**
     * Filling sorted array from 2 another sorted arrays.
     * @param a - first sorted initializing array.
     * @param b - second sorted initializing array.
     * @return res - resulting sorted array.
     */
    public int[] fillSortedArray(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (k < (a.length + b.length)) {
            if (i < a.length && j < b.length) {
                if (a[i] <= b[j]) {
                    res[k] = a[i];
                    i++;
                } else {
                    res[k] = b[j];
                    j++;
                }
            } else if (i >= a.length) {
                res[k] = b[j];
                j++;
            } else if (j >= b.length) {
                res[k] = a[i];
                i++;
            }
            k++;
        }
        return res;
    }
}
