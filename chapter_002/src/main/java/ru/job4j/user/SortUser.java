package ru.job4j.user;

import java.util.*;

/**
 * SortUser - class sorting collections of User objects
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * The method sorting the list of Users according to the used comparator.
     * @return sorted TreeSet producted from User List.
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<User>(users);
    }

    /**
     * The method sorting the List of Users by the name length from short to long names.
     * @return - sorted users List.
     */
    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return users;
    }

    /**
     * The method sorting the List of Users at first by the name length (from short to long names)
     * and then by the age (from young to old).
     * @return - sorted users List.
     */
    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() > o2.getName().length() ? 1
                        : o1.getName().length() < o2.getName().length() ? -1
                        : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return users;
    }
}
