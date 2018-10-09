package ru.job4j.tracker;

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

    /**
     * Constructor.
     * @param input - using I/O class
     * @param tracker - connected Tracker object
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /** function initializing program */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
        } while (!"y".equals(menu.select(this.input.ask("select: "))));
    }

    /* main function - entry point in programm */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}