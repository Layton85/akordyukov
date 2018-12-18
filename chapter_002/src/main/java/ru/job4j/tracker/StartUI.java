package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * StartUI - main class with entry point in programm.
 * StartUI provides console user interface for tracking system.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /** reference to the I/O interface */
    private final Input input;

    /** reference to the Tracker object */
    private final Tracker tracker;

    /** flag of working program condition: true - program working, false - stopped. */
    private boolean working = true;
    private MenuTracker menu;

    /**
     * Constructor.
     * @param input - using I/O class
     * @param tracker - connected Tracker object
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /** the method initializing program */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        List<Integer> range = this.fillRange(menu);
        do {
            menu.show();
            menu.select(this.input.ask("select: ", range));
        } while (this.working);
    }

    /** The method stops the program */
    public void stop() {
        this.working = false;
    }

    /**
     *  The method fills range array for the menu.
     * @param menu - MenuTracker object.
     * @return - filled range array.
     */
    private List<Integer> fillRange(MenuTracker menu) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            result.add(i);
        }
        return result;
    }

    /* main function - entry point in programm */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}