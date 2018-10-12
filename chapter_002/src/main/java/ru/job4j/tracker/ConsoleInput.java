package ru.job4j.tracker;

import java.util.Scanner;

/**
 * ConsoleInput - class for input and output information in console.
 * ConsoleInput implements interface Input.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {
    /** System object for work with user input */
    private Scanner scanner = new Scanner(System.in);

    /**
     * The method implements method String ask(String question) from interface Input.
     * The method outputs in console some text request and waiting for user input.
     * @param question - programm question text.
     * @return user input string.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * The method implements method int ask(String question, int[] range) from interface Input.
     * The method returns a key entered by user after request.
     * @param question - programm question text.
     * @param range - limit of the resolved values of the key.
     * @return entered by user menu key.
     */
    @Override
    public int ask(String question, int[] range) {
        int result = -1;
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            result = key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
        return result;
    }
}
