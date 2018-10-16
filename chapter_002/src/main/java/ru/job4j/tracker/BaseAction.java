package ru.job4j.tracker;

/**
 * BaseAction - class provides basics UserAction methods.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseAction implements UserAction {
    /** key of the menu option */
    private final int key;

    /** the menu option name */
    private final String name;

    /**
     * Constructor
     * @param key - key of the menu option
     * @param name - the menu option name
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Override method implements method int key() from the interface UserAction.
     * @return key of this menu option.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Override method implements method String info() from the interface UserAction.
     * @return - information about this menu option.
     */
    @Override
    public String info() {
        return String.format("%s : %s", this.key, this.name);
    }
}
