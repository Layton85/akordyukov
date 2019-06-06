package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * RoleStoreTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RoleStoreTest {
    private RoleStore store;
    
    @Before
    public void setUp() {
        store = new RoleStore(3);
        store.add(new Role("5"));
        store.add(new Role("6"));
        store.add(new Role("7"));
    }

    @Test
    public void whenFindByID() {
        assertThat(store.findById("5").equals(new Role("5")), is(true));
        assertThat(store.findById("7").equals(new Role("7")), is(true));
        assertThat(store.findById("6").equals(new Role("6")), is(true));
    }

    @Test
    public void whenReplace() {
        store.replace("5", new Role("15"));
        store.replace("7", new Role("17"));
        store.replace("6", new Role("16"));
        assertThat(store.findById("17").equals(new Role("17")), is(true));
        assertThat(store.findById("16").equals(new Role("16")), is(true));
        assertThat(store.findById("15").equals(new Role("15")), is(true));
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