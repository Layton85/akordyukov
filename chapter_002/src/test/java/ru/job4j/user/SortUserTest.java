package ru.job4j.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SortUserTest
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    @Test
    public void whenNoAnyUsersThenEmptyTreeSet() {
        List<User> users = new ArrayList<User>();
        Set<User> tree = new TreeSet<>(new SortUser().sort(users));
        assertThat(tree.isEmpty(), is(true));
    }

    @Test
    public void whenOneUserThenOneElementInTreeSet() {
        List<User> users = new ArrayList<User>();
        users.add(new User("Maria", 21));
        Set<User> tree = new TreeSet<>(new SortUser().sort(users));
        Set<User> expected = Set.of(
                new User("Maria", 21)
        );
        assertThat(expected.equals(tree), is(true));
    }

    @Test
    public void whenTwoAgeSortedUsersThenTwoAgeSortedUsersInTreeSet() {
        List<User> users = new ArrayList<User>();
        users.add(new User("Maria", 21));
        users.add(new User("Ivan", 27));
        Set<User> tree = new TreeSet<>(new SortUser().sort(users));
        Set<User> expected = Set.of(
                new User("Maria", 21),
                new User("Ivan", 27)
        );
        assertThat(expected.equals(tree), is(true));
    }

    @Test
    public void whenTwoNonAgeSortedUsersThenTwoAgeSortedUsersInTreeSet() {
        List<User> users = new ArrayList<User>();
        users.add(new User("Ivan", 27));
        users.add(new User("Maria", 21));
        Set<User> tree = new TreeSet<>(new SortUser().sort(users));
        Set<User> expected = Set.of(
                new User("Maria", 21),
                new User("Ivan", 27)
        );
        assertThat(expected.equals(tree), is(true));
    }

    @Test
    public void whenThreeNonAgeSortedUsersThenThreeAgeSortedUsersInTreeSet() {
        List<User> users = new ArrayList<User>();
        users.add(new User("Ivan", 27));
        users.add(new User("Maria", 21));
        users.add(new User("Nick", 32));
        Set<User> tree = new TreeSet<>(new SortUser().sort(users));
        Set<User> expected = Set.of(
                new User("Maria", 21),
                new User("Ivan", 27),
                new User("Nick", 32)
        );
        assertThat(expected.equals(tree), is(true));
    }

    @Test
    public void whenTwoUsersWithEqualAgeThenReturnedInSetOnlyFirstUser() {
        List<User> users = new ArrayList<>();
        users.add(new User("Jim", 30));
        users.add(new User("Vlad", 30));
        Set<User> tree = new TreeSet<>(new SortUser().sort(users));
        Set<User> expected = Set.of(
                new User("Jim", 30)
        );
        assertThat(expected.equals(tree), is(true));
    }

    @Test
    public void whenThreeUsersWithDifferentNamesThenSortedByNameLength() {
        List<User> users = new ArrayList<User>();
        users.add(new User("Karina"));
        users.add(new User("Jim"));
        users.add(new User("Vlad"));
        new SortUser().sortNameLength(users);
        List<User> expected = List.of(
                new User("Jim"),
                new User("Vlad"),
                new User("Karina")
        );
        assertThat(expected.equals(users), is(true));
    }

    @Test
    public void whenFourUsersWithDifferentNamesAndAgesThenSortedByAllFields() {
        List<User> users = new ArrayList<User>();
        users.add(new User("Sergey", 25));
        users.add(new User("Ivan", 30));
        users.add(new User("Sergey", 20));
        users.add(new User("Ivan", 25));
        new SortUser().sortByAllFields(users);
        List<User> expected = List.of(
                new User("Ivan", 25),
                new User("Ivan", 30),
                new User("Sergey", 20),
                new User("Sergey", 25)
        );
        assertThat(expected.equals(users), is(true));
    }
}
