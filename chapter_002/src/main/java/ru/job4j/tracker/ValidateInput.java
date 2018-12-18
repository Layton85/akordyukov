package ru.job4j.tracker;

import java.util.List;

/**
 * Class ValidateInput wraps Input for adding new behavior (pfattern Decorator)
 * ValidateInput implements Input.
 * ValidateInput provides validate of user-entered values.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput implements Input {
    /** Used Input interface */
    private final Input input;

    /**
     * Constructor
     * @param input - used Input interface.
     */
    public ValidateInput(final Input input) {
        this.input = input;
    }

    /**
     * Override method.
     * @param question - request text.
     * @return - String answer on question (depends on used Input interface).
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * The method returns menu key using defined Input interface according to accepted range of input values.
     * @param question - programm question text.
     * @param range - limit of the resolved values of the key.
     * @return - entered menu key
     */
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate value.");
            }
        } while (invalid);
        return value;
    }
}
