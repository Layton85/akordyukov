package ru.job4j.tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * Figure3T - the class describing behavior of a cell on the tic-tac-toe game field
 */
public class Figure3T extends Rectangle {
    /**
     * this member innformates about filling cell
     * true - `X-filling`
     * false - `there is no X-filling`
     */
    private boolean markX = false;

    /**
     * this member innformates about filling cell
     * true - `O-filling`
     * false - `there is no O-filling`
     */
    private boolean markO = false;

    /** Constructor without parameters*/
    public Figure3T() {
    }

    /**
     * Constructor.
     * @param markX - information about `X-filling` of cell.
     */
    public Figure3T(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    /**
     * The method changes `X-filling` of cell.
     * @param markX - information about new `X-filling` cell.
     */
    public void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    /**
     * Get-method.
     * @return markX - information about `X-filling` of cell.
     */
    public boolean hasMarkX() {
        return this.markX;
    }

    /**
     * Get-method.
     * @return markO - information about `O-filling` of cell.
     */
    public boolean hasMarkO() {
        return this.markO;
    }
}