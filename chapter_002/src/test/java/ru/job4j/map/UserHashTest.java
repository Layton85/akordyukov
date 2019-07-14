package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * UserTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserHashTest {
    @Test
    public void whenOnlyHashCodeIsOverriden() {
        UserHash user1 = new UserHash("Tom", 4,
                new GregorianCalendar(1975, Calendar.FEBRUARY, 23));
        UserHash user2 = new UserHash("Tom", 4,
                new GregorianCalendar(1975, Calendar.FEBRUARY, 23));
        UserHash user3 = new UserHash("Mark", 4,
                new GregorianCalendar(1975, Calendar.FEBRUARY, 23));
        Map<UserHash, Object> map = new HashMap<>();
        map.put(user1, null);
        map.put(user2, null);
        map.put(user3, null);
        System.out.println(user1.hashCode() + System.lineSeparator());
        System.out.println(user2.hashCode() + System.lineSeparator());
        System.out.println(user3.hashCode() + System.lineSeparator());
        System.out.println(map);
    }
}