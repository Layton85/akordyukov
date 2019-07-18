package ru.job4j.map;

/**
 * UserBadHash - class shows the User with awful hashCode() method.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserBadHash {
    private int age;
    private String name;

    public UserBadHash(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBadHash that = (UserBadHash) o;

        if (age != that.age) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
