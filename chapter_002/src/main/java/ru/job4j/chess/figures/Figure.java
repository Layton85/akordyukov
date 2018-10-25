package ru.job4j.chess.figures;

/**
 * Figure - abstract class which describes chess figures behavior.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {
    /** Figure position on the chess board. */
    final Cell position;

    /**
     * Figure constructor.
     * @param position - Figure position on the chess board.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Get method for the figure position.
     * @return - Figure position on the chess board.
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * The method returns the way of figure during the movement.
     * The first point of the way according the next cell after start point.
     * Tke last point of the way according the destination cell.
     * @param source - start position.
     * @param dest - destination position.
     * @return - an array of cells between start and destination.
     * @throws ImpossibleMoveException
     */
    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Method cut some existing figure from its cell and paste it into the destination cell.
     * @param dest - destination cell.
     * @return - new figure object.
     */
    public abstract Figure copy(Cell dest);

    /**
     * Get method for the name of icon image of figure.
     * @return - the name of icon image of figure.
     */
    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }
}
