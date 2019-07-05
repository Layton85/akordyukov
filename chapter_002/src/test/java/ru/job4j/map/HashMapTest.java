package ru.job4j.map;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMapTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class HashMapTest {
    @Test
    public void whenElementsWithoutOverridingHashCodeAndEquals() {
        CollectionElements.User first = new CollectionElements.User("Alex");
        CollectionElements.User second = new CollectionElements.User("Alex");
        Map<CollectionElements.User, String> map = new HashMap<>();
        map.put(first, first.getName());
        map.put(second, second.getName());
        System.out.println(map);
        assertThat(first.equals(second), is(false));
        //assertThat(first, is(second)); //not success result
        assertThat(map.size(), is(2));
    }

    @Test
    public void whenOnlyHashCodeOverrided() {
        CollectionElements.UserHash first = new CollectionElements.UserHash("Alex");
        CollectionElements.UserHash second = new CollectionElements.UserHash("Alex");
        Map<CollectionElements.UserHash, String> map = new HashMap<>();
        map.put(first, first.getName());
        map.put(second, second.getName());
        System.out.println(map);
        assertThat(first.equals(second), is(false));
        //assertThat(first, is(second)); //not success result
        assertThat(map.size(), is(2));
    }

    //Сommitted because of the checkstyle requirements.
//    @Test
//    public void whenOnlyEqualsOverrided() {
//        CollectionElements.UserEquals first = new CollectionElements.UserEquals("Alex");
//        CollectionElements.UserEquals second = new CollectionElements.UserEquals("Alex");
//        Map<CollectionElements.UserEquals, String> map = new HashMap<>();
//        map.put(first, first.getName());
//        map.put(second, second.getName());
//        System.out.println(map);
//        assertThat(first.equals(second), is(true));
//        assertThat(first, is(second));
//        assertThat(map.size(), is(2));
//    }

    @Test
    public void whenHashCodeAndEqualsOverrided() {
        CollectionElements.UserHashEquals first = new CollectionElements.UserHashEquals("Alex");
        CollectionElements.UserHashEquals second = new CollectionElements.UserHashEquals("Alex");
        Map<CollectionElements.UserHashEquals, String> map = new HashMap<>();
        map.put(first, first.getName());
        map.put(second, second.getName());
        System.out.println(map);
        assertThat(first.equals(second), is(true));
        assertThat(first, is(second));
        assertThat(map.size(), is(1));
    }
}
