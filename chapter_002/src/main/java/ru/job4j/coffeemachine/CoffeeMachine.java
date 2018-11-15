package ru.job4j.coffeemachine;

import java.util.*;

/**
 * Tracker - class describes operations of the coffeemachine automat.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachine {
    /**
     * The method describes a process of giving change in the coffeemachine automat.
     * @param value - note face value
     * @param price - product price
     * @return - an array of money values for the change with the smallest possible number of elements.
     * If the value is equal or it is more than price the method returns an empty array.
     */
    int[] changes(int value, int price) {
        List<Integer> changes = new ArrayList<Integer>();
        int[] faceValues = new int[] {5000, 2000, 1000, 500, 200, 100, 50, 10, 5, 2, 1};
        int remainder = value - price;
        while (remainder > 0) {
            for (int i : faceValues) {
                if (i <= remainder) {
                    remainder -= i;
                    changes.add(i);
                    break;
                }
            }
        }
        int[] result = new int[changes.size()];
        for (int i = 0; i < changes.size(); i++) {
            result[i] = changes.get(i);
        }
        return result;
    }
}
