package ru.job4j.tracker;

import java.util.List;

/**
 * StubInput - class emulated user`s input.
 * StubInput implements interface Input.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {
    /** sequence of the requests inputted by user*/
    private final List<String> value;
    /** number of the current request of the user */
    private int position;

    /**
     * Constructor.
     * @param value - sequence of the requests inputted by user.
     */
    public StubInput(List<String> value) {
        this.value = value;
    }

    /**
     * The method implements method ask(String question) from interface Input.
     * @param question - programm question text.
     * @return returns emulated user`s requests corresponded with input questions.
     */
    @Override
    public String ask(String question) {
        return this.value.get(this.position++);
    }

    /**
     * The method implements method int ask(String question, List<Integer> range) from interface Input.
     * The method returns emulated menu key.
     * @param question - programm question text.
     * @param range - limit of the resolved values of the key.
     * @return emulated menu key.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range.");
        }
        return key;
    }
}
