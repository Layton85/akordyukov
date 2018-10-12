package ru.job4j.tracker;

/**
 * Input - interface class facilitating work with I/O System.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public interface Input {
    /**
     * The method receive some string request and return string reply for them.
     * @param question - request text.
     * @return reply to the question.
     */
    String ask(String question);

    /**
     * The method receive some string request and return int reply for them - key.
     * @param question - programm question text.
     * @param range - limit of the resolved values of the key.
     * @return key.
     */
    int ask(String question, int[] range);
}
