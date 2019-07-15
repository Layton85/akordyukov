package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * UserEquals - class shows the model of User
 * with overridden method equals() and not overridden hashCode().
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserEquals {
    /** User name */
    private String name;

    /** the number of children */
    private int children;

    /** birthday data */
    private GregorianCalendar birthday;

    /**
     * Constructor
     * @param name - User name
     * @param children - the number of children
     * @param birthday - birthday data
     */
    public UserEquals(String name, int children, GregorianCalendar birthday) {
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
     * @return - the number of children
     */
    public int getChildren() {
        return children;
    }

    /**
     * Get-method
     * @return - birthday data
     */
    public GregorianCalendar getBirthday() {
        return birthday;
    }

    /**
     * Overridden method equals(..).
     * @param o - object to compare.
     * @return - comparison result.
     */
    @Override
    public boolean equals(Object o) {
        boolean result = true;
        if (this != o) {
            if (o == null || getClass() != o.getClass()) {
                result = false;
            } else {
                UserEquals that = (UserEquals) o;
                if ((this.getChildren() != that.getChildren())
                        || (this.getName() != null ? !this.getName().equals(that.getName()) : that.getName() != null)) {
                    result = false;
                } else {
                    result = this.getBirthday() != null
                            ? this.getBirthday().equals(that.getBirthday())
                            : that.getBirthday() == null;
                }
            }
        }
        return result;
    }

    /**
     * Overriden method toString().
     * @return - User description.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday: " + this.birthday.get(Calendar.YEAR)
                + ", " + (this.birthday.get(Calendar.MONTH) + 1)
                + ", " + this.birthday.get(Calendar.DATE)
                + '}';
    }
}