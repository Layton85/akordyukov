package ru.job4j.chess;

import ru.job4j.chess.figures.*;

/**
 * Board - class describes chess board.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /** Chess figures storage */
    private final Figure[] figures = new Figure[32];
    /** The number of figures on the board */
    private int index = 0;

    /**
     * Method adds a new figure on the board.
     * @param figure - the figure which is added on the board.
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * The method produces the process of movement of figure from its current position on the board to the destination position.
     * 1. At first method checks that the figure existing on the start position. If figure doesn`t exist the method throws FigureNotFoundException.
     * 2. Then the method produces the way of figure movement. If the way is incorrect for this figure throws ImpossibleMoveException.
     * 3. After that the method checks that the way of figure is not occupied by other figures. If it is occupied the method throws OccupiedWayException.
     * 4. Finally the method recording the figure into the destination cell (the start cell becomes empty).
     * variable number - is a serial number of figure in the figures storage.
     *
     * @param source - start position.
     * @param dest - destination position.
     * @throws ImpossibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    public void move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure figure = null;
        int number = 0;
        boolean found = false;
        for (int i = 0; i < this.index; i++) {
            if ((this.figures[i].getPosition().x == source.x) && (this.figures[i].getPosition().y == source.y)) {
                figure = this.figures[i];
                found = true;
                break;
            } else {
                number++;
            }
        }
        if (!found) {
            throw new FigureNotFoundException("No any figure");
        }
        Cell[] way = figure.way(source, dest);
        for (Cell cell : way) {
            for (int i = 0; i < this.index; i++) {
                if ((cell.x == this.figures[i].getPosition().x) && (cell.y == this.figures[i].getPosition().y)) {
                    throw new OccupiedWayException("Occupied way");
                }
            }
        }
        this.figures[number] = figure.copy(dest);
    }

    /**
     * The method cleans the board from all figures.
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }
}
