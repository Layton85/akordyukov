package ru.job4j.chess.figures;

/**
 * FigureNotFoundException - class for exceptions.
 * FigureNotFoundException extends RuntimeException class.
 * FigureNotFoundException throws when selected Figure doesn`t exist.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Constructor
     * @param msg - exception message.
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
