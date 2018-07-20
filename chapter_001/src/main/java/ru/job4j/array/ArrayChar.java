package ru.job4j.array;

/**
 * ArrayChar - class cover over the string.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayChar {
    /**
     * Char array initialized with the initializing string.
     */
    private char[] data;

    /**
     * Constructor.
     * @param line initializing string.
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Checks that the word begins with a prefix.
     * @param prefix .
     * @return true - if the line begin with the prefix, false - if not.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0; i < value.length; i++) {
            if (value[i] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
