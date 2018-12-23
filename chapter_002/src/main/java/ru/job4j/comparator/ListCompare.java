package ru.job4j.comparator;

import java.util.Comparator;

/**
 * TrackerListCompare - class for string comparations.
 * Class implements interface Comparator<String>.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ListCompare implements Comparator<String> {
    /**
     * Override method. It Describes lexicographic comparation of its arguments (two strings).
     * This method is a part of education program and doesn`t use the method String.compareTo(String anotherString),
     * instead of this it use the methods String.charAt(int index), Character.compare(char left, char right) and other.
     * @param left - string to compare
     * @param right - another string to compare
     * @return - result of lexicographic comparation:
     *  -1  - when first lexicographically less then right,
     *   0  - when first and right are lexicographically same,
     *   1  - when first lexicographically greater then right.
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        if (!left.equals(right)) {
            for (int i = 0; i < Integer.min(left.length(), right.length()); i++) {
                if (Character.compare(left.charAt(i), right.charAt(i)) > 0) {
                    result = 1;
                    break;
                } else if (Character.compare(left.charAt(i), right.charAt(i)) < 0) {
                    result = -1;
                    break;
                }
            }
            if (result == 0) {
                if (left.length() < right.length()) {
                    result = -1;
                } else {
                    result = 1;
                }
            }
        }
        return result;
    }
}
