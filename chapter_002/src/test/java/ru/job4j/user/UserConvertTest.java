package ru.job4j.user;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * UserConvertTest
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {
    @Test
    public void when0UsersInListThen0UsersInHashMap() {
        UserConvert converter = new UserConvert();
        List<User> list = List.of();
        HashMap<Integer, User> resultMap = converter.process(list);
        assertThat(resultMap.isEmpty(), is(true));
    }

    @Test
    public void when1UserInListThen1UserInHashMap() {
        UserConvert converter = new UserConvert();
        List<User> list = List.of(new User(1, "Nicky", "Boston"));
        HashMap<Integer, User> resultMap = converter.process(list);
        HashMap<Integer, User> expectedMap = new HashMap<>();
        expectedMap.put(1, new User(1, "Nicky", "Boston"));
        assertThat(resultMap.equals(expectedMap), is(true));
    }

    @Test
    public void when2UsersInListThen2UsersInHashMap() {
        UserConvert converter = new UserConvert();
        List<User> list = List.of(
                new User(1, "Nicky", "Boston"),
                new User(2, "Lucas", "Warsaw")
        );
        HashMap<Integer, User> resultMap = converter.process(list);
        HashMap<Integer, User> expectedMap = new HashMap<>();
        expectedMap.put(1, new User(1, "Nicky", "Boston"));
        expectedMap.put(2, new User(2, "Lucas", "Warsaw"));
        assertThat(resultMap.equals(expectedMap), is(true));
    }

    @Test
    public void when3UsersWithDisorderedKeysInListThen3UsersInHashMap() {
        UserConvert converter = new UserConvert();
        List<User> list = List.of(
                new User(-100, "Nicky", "Boston"),
                new User(99, "Lucas", "Warsaw"),
                new User(0, "Li", "Beijing")
        );
        HashMap<Integer, User> resultMap = converter.process(list);
        HashMap<Integer, User> expectedMap = new HashMap<>();
        expectedMap.put(-100, new User(-100, "Nicky", "Boston"));
        expectedMap.put(99, new User(99, "Lucas", "Warsaw"));
        expectedMap.put(0, new User(0, "Li", "Beijing"));
        assertThat(resultMap.equals(expectedMap), is(true));
    }
}