package ru.job4j.chess.figures;

/**
 * ImpossibleMoveException - class for exceptions.
 * ImpossibleMoveException extends RuntimeException class.
 * ImpossibleMoveException throws when the Figure is tried to be moved incorrectly.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Constructor
     * @param msg - exception message.
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
