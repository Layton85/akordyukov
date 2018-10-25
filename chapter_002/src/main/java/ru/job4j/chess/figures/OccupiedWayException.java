package ru.job4j.chess.figures;

/**
 * OccupiedWayException - class for exceptions.
 * OccupiedWayException extends RuntimeException class.
 * OccupiedWayException throws when Figure can`t reach specified destination because of another figure on the way.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Constructor
     * @param msg - exception message.
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
