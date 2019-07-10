package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * UserTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserTest {

    @Test
    public void whenNonNullMembersThenHashCodeCalculating() {
        User user = new User("Tom", 4,
                new GregorianCalendar(1975, Calendar.FEBRUARY, 23));
        int resultHash = user.hashCode();
        System.out.println(resultHash);
        assertThat(user.hashCode(), is(resultHash));
    }

    @Test
    public void whenNullMembersThenHashCodeCalculatingWithoutExceptions() {
        User user = new User(null, 4, null);
        System.out.println(user.hashCode());
        assertThat(user.hashCode(), is(124));
    }
}