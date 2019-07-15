package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * UserHashEquals - class shows the model of User
 * with the overridden methods hashCode() and equals().
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserHashEquals {
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
    public UserHashEquals(String name, int children, GregorianCalendar birthday) {
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
     * Overridden method hashCode().
     * @return - User`s hash code.
     */
    @Override
    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + Integer.hashCode(this.children);
        result = 31 * result + (this.birthday != null ? this.birthday.hashCode() : 0);
        return result;
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
            if (o == null || this.getClass() != o.getClass()) {
                result = false;
            } else {
                UserHashEquals that = (UserHashEquals) o;
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
     * Overridden method toString().
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
