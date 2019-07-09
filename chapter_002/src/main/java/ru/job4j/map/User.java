package ru.job4j.map;

import java.util.Calendar;

/**
 * User - class shows the model of User.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class User {
    /** User name */
    private String name;

    /** the number of childrens */
    private int children;

    /** birthday data */
    private Calendar birthday;

    /**
     * Constructor
     * @param name - User name
     * @param children - the number of childrens
     * @param birthday - birthday data
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Get-method
     * @return - User name
     */
    public String getName() {
        return name;
    }

    /**
     * Get-method
     * @return - the number of childrens
     */
    public int getChildren() {
        return children;
    }

    /**
     * Get-method
     * @return - birthday data
     */
    public Calendar getBirthday() {
        return birthday;
    }
}
