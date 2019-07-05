package ru.job4j.map;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * HashSetTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class HashSetTest {
    @Test
    public void whenElementsWithoutOverridingHashCodeAndEquals() {
        CollectionElements.User first = new CollectionElements.User("Alex");
        CollectionElements.User second = new CollectionElements.User("Alex");
        Set<CollectionElements.User> set = new HashSet<>();
        set.add(first);
        set.add(second);
        System.out.println(set);
        assertThat(first.equals(second), is(false));
        assertThat(set.size(), is(2));
    }

    @Test
    public void whenOnlyHashCodeOverrided() {
        CollectionElements.UserHash first = new CollectionElements.UserHash("Alex");
        CollectionElements.UserHash second = new CollectionElements.UserHash("Alex");
        Set<CollectionElements.UserHash> set = new HashSet<>();
        set.add(first);
        set.add(second);
        System.out.println(set);
        assertThat(first.equals(second), is(false));
        assertThat(set.size(), is(2));
    }

    //Сommitted because of the checkstyle requirements.
//    @Test
//    public void whenOnlyEqualsOverrided() {
//        CollectionElements.UserEquals first = new CollectionElements.UserEquals("Alex");
//        CollectionElements.UserEquals second = new CollectionElements.UserEquals("Alex");
//        Set<CollectionElements.UserEquals> set = new HashSet<>();
//        set.add(first);
//        set.add(second);
//        System.out.println(set);
//        assertThat(first.equals(second), is(true));
//        assertThat(first, is(second));
//        assertThat(set.size(), is(2));
//    }

    @Test
    public void whenHashCodeAndEqualsOverrided() {
        CollectionElements.UserHashEquals first = new CollectionElements.UserHashEquals("Alex");
        CollectionElements.UserHashEquals second = new CollectionElements.UserHashEquals("Alex");
        Set<CollectionElements.UserHashEquals> set = new HashSet<>();
        set.add(first);
        set.add(second);
        System.out.println(set);
        assertThat(first.equals(second), is(true));
        assertThat(first, is(second));
        assertThat(set.size(), is(1));
    }
}
