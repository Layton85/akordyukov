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

    /** flag of working program condition: true - program working, false - stopped. */
    private boolean working = true;

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
        do {
            menu.show();
            menu.select(this.input.ask("select: "));
        } while (this.working);
    }

    /** The method stops the program */
    public void stop() {
        this.working = false;
    }

    /* main function - entry point in programm */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}