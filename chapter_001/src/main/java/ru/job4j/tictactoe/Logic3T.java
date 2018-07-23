package ru.job4j.tictactoe;

import java.util.function.Predicate;

/**
 * Logic3T - the class containing logic of a game a tic-tac-toe
 */
public class Logic3T {
    /** class-member table describing a game field */
    private final Figure3T[][] table;

    /**
     * Constructor.
     * @param table - a game field.
     */
    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Auxiliary universal method getting information about filling one row, column or diagonal on the game field
     * @param predicate - function setting the symbol of filling of a cell (X or O).
     * @param startX - start x coordinate
     * @param startY - start y coordinate
     * @param deltaX - characteristic of movement on X
     * @param deltaY - characteristic of movement on Y
     * @return result - information on filling of the specified space of the game field to the set contents.
     *                  true - contents correspond to the specified parameters,
     *                  false - contents don`t correspond to the specified parameters.
     */
    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Method getting information about winner.
     * @return result - true - X won, false - X didn`t win.
     */
    public boolean isWinnerX() {
        boolean result = false;
        if (this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1)) {
            result = true;
        } else {
            for (int index = 0; index != this.table.length; index++) {
                if (this.fillBy(Figure3T::hasMarkX, 0, index, 1, 0)
                        || this.fillBy(Figure3T::hasMarkX, index, 0, 0, 1)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Method getting information about winner.
     * @return result - true - O won, false - O didn`t win.
     */
    public boolean isWinnerO() {
        boolean result = false;
        if (this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1)) {
            result = true;
        } else {
            for (int index = 0; index != this.table.length; index++) {
                if (this.fillBy(Figure3T::hasMarkO, 0, index, 1, 0)
                        || this.fillBy(Figure3T::hasMarkO, index, 0, 0, 1)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Method getting information about empty area on the game field.
     * @return result - true - there are free cells, false - there are no free cells.
     */
    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i != this.table.length && !result; i++) {
            for (int j = 0; j != this.table.length; j++) {
                if (!(this.table[i][j].hasMarkO() || this.table[i][j].hasMarkX())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}