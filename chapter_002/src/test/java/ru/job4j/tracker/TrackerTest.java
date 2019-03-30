package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TrackerTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription1", 123L);
        tracker.add(item);
        assertThat(tracker.getAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription1", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteOnlyItemThenTrackerHasNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        tracker.delete(tracker.getAll().get(0).getId());
        assertThat(tracker.getAll().isEmpty(), is(true));
    }

    @Test
    public void whenDeleteFirstOfThreeItemsThenTrackerHasSecondAndThirdItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        Item item3 = new Item("test3", "testDescription3", 125L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = List.of(
                new Item("test2", "testDescription2", 124L),
                new Item("test3", "testDescription3", 125L)
        );
        expected.get(0).setId(tracker.getAll().get(1).getId());
        expected.get(1).setId(tracker.getAll().get(2).getId());
        tracker.delete(tracker.getAll().get(0).getId());
        assertThat(expected.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenDeleteSecondOfThreeItemsThenTrackerHasFirstAndThirdItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        Item item3 = new Item("test3", "testDescription3", 125L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = List.of(
                new Item("test1", "testDescription1", 123L),
                new Item("test3", "testDescription3", 125L)
        );
        expected.get(0).setId(tracker.getAll().get(0).getId());
        expected.get(1).setId(tracker.getAll().get(2).getId());
        tracker.delete(tracker.getAll().get(1).getId());
        assertThat(expected.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenDeleteThirdOfThreeItemsThenTrackerHasFirstAndSecondItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        Item item3 = new Item("test3", "testDescription3", 125L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = List.of(
                new Item("test1", "testDescription1", 123L),
                new Item("test2", "testDescription2", 124L)
        );
        expected.get(0).setId(tracker.getAll().get(0).getId());
        expected.get(1).setId(tracker.getAll().get(1).getId());
        tracker.delete(tracker.getAll().get(2).getId());
        assertThat(expected.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenGetAllItemsThenTrackerGivesNoAnyItems() {
        Tracker tracker = new Tracker();
        List<Item> nothing = List.of();
        assertThat(nothing.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenGetAllItemsThenTrackerGivesOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription1", 123L);
        tracker.add(item);
        List<Item> expected = List.of(new Item("test1", "testDescription1", 123L));
        expected.get(0).setId(tracker.getAll().get(0).getId());
        assertThat(expected.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenGetAllItemsThenTrackerGivesTwoItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        tracker.add(item1);
        tracker.add(item2);
        List<Item> expected = List.of(
                new Item("test1", "testDescription1", 123L),
                new Item("test2", "testDescription2", 124L)
        );
        expected.get(0).setId(tracker.getAll().get(0).getId());
        expected.get(1).setId(tracker.getAll().get(1).getId());
        assertThat(expected.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenFindByNameThenTrackerGivesRightItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription2", 124L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription3", 125L);
        tracker.add(item3);
        List<Item> expected = List.of(new Item("test2", "testDescription2", 124L));
        expected.get(0).setId(tracker.getAll().get(1).getId());
        assertThat(expected.equals(tracker.findByName("test2")), is(true));
    }

    @Test
    public void whenFindByNoExistingNameThenTrackerGivesNoItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription2", 124L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription3", 125L);
        tracker.add(item3);
        List<Item> expected = List.of();
        assertThat(expected.equals(tracker.findByName("qwerty")), is(true));
    }

    @Test
    public void whenFindByNameThenTrackerGivesRightItemsNearBeginning() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test", "testDescription1", 123L);
        tracker.add(item1);
        Item item2 = new Item("test", "testDescription2", 124L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription3", 125L);
        tracker.add(item3);
        List<Item> expected = List.of(
                new Item("test", "testDescription1", 123L),
                new Item("test", "testDescription2", 124L)
        );
        expected.get(0).setId(tracker.getAll().get(0).getId());
        expected.get(1).setId(tracker.getAll().get(1).getId());
        assertThat(expected.equals(tracker.findByName("test")), is(true));
    }

    @Test
    public void whenFindByNameThenTrackerGivesRightItemsNearTheEnd() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("tes1", "testDescription1", 123L);
        tracker.add(item1);
        Item item2 = new Item("test", "testDescription2", 124L);
        tracker.add(item2);
        Item item3 = new Item("test", "testDescription3", 125L);
        tracker.add(item3);
        List<Item> expected = List.of(
                new Item("test", "testDescription2", 124L),
                new Item("test", "testDescription3", 125L)
        );
        expected.get(0).setId(tracker.getAll().get(1).getId());
        expected.get(1).setId(tracker.getAll().get(2).getId());
        assertThat(expected.equals(tracker.findByName("test")), is(true));
    }

    @Test
    public void whenFindByNameThenTrackerGivesAllItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test", "testDescription1", 123L);
        tracker.add(item1);
        Item item2 = new Item("test", "testDescription2", 124L);
        tracker.add(item2);
        Item item3 = new Item("test", "testDescription3", 125L);
        tracker.add(item3);
        List<Item> expected = List.of(
                new Item("test", "testDescription1", 123L),
                new Item("test", "testDescription2", 124L),
                new Item("test", "testDescription3", 125L)
        );
        expected.get(0).setId(tracker.getAll().get(0).getId());
        expected.get(1).setId(tracker.getAll().get(1).getId());
        expected.get(2).setId(tracker.getAll().get(2).getId());
        assertThat(expected.equals(tracker.findByName("test")), is(true));
    }

    @Test
    public void whenFindByIdThenTrackerGivesRightItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription2", 124L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription3", 125L);
        tracker.add(item3);
        Item expected = new Item("test2", "testDescription2", 124L);
        expected.setId(tracker.getAll().get(1).getId());
        assertThat(tracker.findById(tracker.getAll().get(1).getId()), is(expected));
    }

    @Test
    public void whenFindByNoExistingIdThenTrackerGivesNoItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription2", 124L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription3", 125L);
        tracker.add(item3);
        Item expected = null;
        assertThat(tracker.findById("id"), is(expected));
    }
}