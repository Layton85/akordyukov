package ru.job4j.user;

import java.util.Objects;

/**
 * User - class describes some user.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {
    /** User id */
    private int id = 0;
    /** User name */
    private String name = null;
    /** User age */
    private int age = 0;
    /** User city */
    private String city = null;

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
     * Constructor
     * @param name - User name
     * @param age - User age
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Get-method
     * @return - User id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get-method
     * @return - User age
     */
    public int getAge() {
        return this.age;
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

    /**
     * Override method toString() for the class User.
     * @return - string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", age=" + age
                + ", city='" + city + '\''
                + '}';
    }

    /**
     * Override method compareTo(T o) for the class User
     * @param o - another User object for the comparation
     * @return - a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(User o) {
        //return this.age > o.getAge() ? 1 : this.age < o.getAge() ? -1 : 0; //Integer.valueOf(this.age).compareTo(o.getAge());
        return Integer.compare(this.age, o.getAge());
        //return Integer.valueOf(this.age).compareTo(o.getAge());
    }
}