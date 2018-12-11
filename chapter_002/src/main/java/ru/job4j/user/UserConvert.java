package ru.job4j.user;

import java.util.HashMap;
import java.util.List;

/**
 * UserConvert - class for convertations User objects.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {
    /**
     * The method converts the List of User objects into HashMap
     * @param list - List of User objects
     * @return
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> hMap = new HashMap<>();
        for (User u : list) {
            hMap.put(u.getId(), u);
        }
        return hMap;
    }
}
