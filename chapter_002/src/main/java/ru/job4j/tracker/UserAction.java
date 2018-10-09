package ru.job4j.tracker;

/**
 * UserAction - interface for the user actions.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public interface UserAction {
    /**
     * The method returns the key of menu point.
     * @return key of menu point.
     */
    int key();

    /**
     * The main method.
     * @param input Input object.
     * @param tracker  Tracker object.
     * @return the message about execution process.
     */
    String execute(Input input, Tracker tracker);

    /**
     * The method gives information about menu point.
     * @return information about the menu point.
     */
    String info();
}