package ru.job4j.chess.figures;

/**
 * Bishop - class describes chess bishop figure.
 * Bishop extends abstract class Figure and overrides its abstract methods.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Bishop extends Figure {
    /**
     * Bishop Constructor.
     * @param position - bishop position on the chess board.
     */
    public Bishop(Cell position) {
        super(position);
    }

    /**
     * Override abstract method way of abstract class Figure.
     * @param source - start position.
     * @param dest - destination position.
     * @return - an array of cells between start and destination.
     * @throws ImpossibleMoveException - when specified destination is incorrect for the bishop.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!this.isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Impossible movement");
        } else {
            int deltaX = ((dest.x - source.x) > 0) ? 1 : -1;
            int deltaY = ((dest.y - source.y) > 0) ? 1 : -1;
            Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
            for (int i = 0; i < steps.length; i++) {
                steps[i] = Cell.values()[Cell.getOrdinal(source.x + (i + 1) * deltaX, source.y + (i + 1) * deltaY)];
            }
            return steps;
        }
    }

    /**
     * Check-method for the specified destination
     * @param source - start position.
     * @param dest - destination position.
     * @return - true - if start and destination are on the one diagonal, false - in other cases.
     */
    private boolean isDiagonal(Cell source, Cell dest) {
        boolean result = false;
        if (!((source.x - dest.x == 0) && (source.y - dest.y == 0))) {
            result = (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y));
        }
        return result;
    }

    /**
     * Override abstract method copy of abstract class Figure.
     * @param dest - destination cell.
     * @return - new Bishop object.
     */
    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }
}
