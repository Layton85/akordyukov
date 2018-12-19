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
        //Set<User> set = new TreeSet<User>(users);
        return new TreeSet<User>(users); //set;
    }

//    public static void main(String[] args) {
//        SortUser sorted = new SortUser();
//        List<User> users = new ArrayList<User>();
//        users.add(new User("Nick", 32));
//        users.add(new User("Ivan", 27));
//        users.add(new User("Maria", 21));
//        Set<User> tree = new TreeSet<>();
//        tree = sorted.sort(users);
//        System.out.println("users: " + users);
//        System.out.println("tree" + tree);
//    }
}
