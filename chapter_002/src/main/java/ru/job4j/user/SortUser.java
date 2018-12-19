package ru.job4j.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
}
