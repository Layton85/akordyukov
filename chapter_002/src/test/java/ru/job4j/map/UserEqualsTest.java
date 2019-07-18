package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * UserEqualsTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserEqualsTest {
//Сommitted because of the checkstyle requirements.
//    @Test
//    public void whenOnlyEqualsIsOverriden() {
//        UserEquals user1 = new UserEquals("Tom", 4,
//                new GregorianCalendar(1975, Calendar.FEBRUARY, 23));
//        UserEquals user2 = new UserEquals("Tom", 4,
//                new GregorianCalendar(1975, Calendar.FEBRUARY, 23));
//        UserEquals user3 = new UserEquals("Mark", 4,
//                new GregorianCalendar(1975, Calendar.FEBRUARY, 23));
//        Map<UserEquals, Object> map = new HashMap<>();
//        map.put(user1, null);
//        map.put(user2, null);
//        map.put(user3, null);
//        System.out.println("user1.hashCode(): " + user1.hashCode() + System.lineSeparator());
//        System.out.println("user2.hashCode(): " + user2.hashCode() + System.lineSeparator());
//        System.out.println("user3.hashCode(): " + user3.hashCode() + System.lineSeparator());
//        System.out.println(map);
//        assertThat(user1.equals(user2), is(true));
//        assertThat(user1.equals(user3), is(false));
//        assertThat(user2.equals(user3), is(false));
//    }
}