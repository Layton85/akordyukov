package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * UserStoreTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserStoreTest {
    private UserStore store;

    @Before
    public void setUp() {
        store = new UserStore(3);
        store.add(new User("5"));
        store.add(new User("6"));
        store.add(new User("7"));
    }

    @Test
    public void whenFindByID() {
        assertThat(store.findById("5").equals(new User("5")), is(true));
        assertThat(store.findById("7").equals(new User("7")), is(true));
        assertThat(store.findById("6").equals(new User("6")), is(true));
    }

    @Test
    public void whenReplace() {
        store.replace("5", new User("15"));
        store.replace("7", new User("17"));
        store.replace("6", new User("16"));
        assertThat(store.findById("17").equals(new User("17")), is(true));
        assertThat(store.findById("16").equals(new User("16")), is(true));
        assertThat(store.findById("15").equals(new User("15")), is(true));
    }

    @Test
    public void whenDelete() {
        store.delete("6");
        assertNull(store.findById("6"));
        store.delete("5");
        assertNull(store.findById("5"));
        store.delete("7");
        assertNull(store.findById("7"));
    }
}