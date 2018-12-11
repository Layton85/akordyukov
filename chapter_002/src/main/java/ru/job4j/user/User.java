package ru.job4j.user;

import java.util.Objects;

/**
 * User - class describes some user.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class User {
    /** User id */
    private int id;
    /** User name */
    private String name;
    /** User city */
    private String city;

    /**
     * Constructor
     * @param id - User id
     * @param name - User name
     * @param city - User city
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Get-method
     * @return - User id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Override method equals() for the class User
     * @param o - another User Object
     * @return - result of comparation of two objects: true - equal, false - non-equal.
     */
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            User user = (User) o;
            result = (
                    this.id == user.id
                    && Objects.equals(this.name, user.name)
                    && Objects.equals(this.city, user.city)
            );
        }
        return result;
    }

    /**
     * Override method hashCode() for the class User
     * @return - User object hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }
}
