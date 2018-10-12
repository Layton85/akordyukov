package ru.job4j.tracker;

/**
 * MenuOutException - class for exceptions.
 * MenuOutException extends RuntimeException class.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MenuOutException extends RuntimeException {
    /**
     * Constructor
     * @param msg - exception message.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
