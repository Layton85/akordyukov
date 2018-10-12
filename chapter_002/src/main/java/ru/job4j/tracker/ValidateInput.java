package ru.job4j.tracker;

/**
 * ValidateInput - class extends ConsoleInput.
 * ValidateInput provides validate of user-entered values.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Overriding method int ask(String question, List<Integer> range) from super class ConsoleInput.
     * @param question - programm question text.
     * @param range - limit of the resolved values of the key.
     * @return - entered menu key
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
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
