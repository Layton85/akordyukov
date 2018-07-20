package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Class for drawing in pseudo-graphic.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * The Method draws the right half of a text-pyramid.
     * @param height - height of the pyramid.
     * @return resulting String of a drawing the right half of a text-pyramid.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * The Method draws the left half of a text-pyramid.
     * @param height - height of the pyramid.
     * @return resulting String of a drawing the left half of a text-pyramid.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * The Method draws a text-pyramid.
     * @param height - height of the pyramid.
     * @return resulting String of a drawing of a text-pyramid.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Auxilliary method for drawing a text-pyramid.
     * @param height - height of the pyramid.
     * @param weight - weight of the pyramid.
     * @param predict - condition for drawing left and right parts of pyramid together.
     * @return resulting String of a drawing of a text-pyramid.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}