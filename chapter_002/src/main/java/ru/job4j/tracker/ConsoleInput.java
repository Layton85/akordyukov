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
     * The method implements method ask(String question) from interface Input.
     * The method outputs in console some text request and waiting for user input.
     * @param question - programm question text.
     * @return user input string.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
