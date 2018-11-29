package ru.job4j.search;

/**
 * Task - class holds information about some task with some priority.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Task {
    /** Task description */
    private String desc;
    /** Task priority */
    private int priority;

    /**
     * Constructor
     * @param desc - Task description
     * @param priority - Task priority
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    /**
     * Get-method
     * @return - Task description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Get-method
     * @return - Task priority
     */
    public int getPriority() {
        return priority;
    }
}
