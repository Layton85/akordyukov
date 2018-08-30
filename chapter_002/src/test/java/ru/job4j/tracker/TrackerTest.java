package ru.job4j.tracker;

import org.junit.Test;

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
        assertThat(tracker.getAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription1", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteOnlyItemThenTrackerHasNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescriptio1", 123L);
        tracker.add(item);
        tracker.delete(tracker.getAll()[0].getId());
        Item[] empty = new Item[0];
        assertArrayEquals(empty, tracker.getAll());
    }

    @Test
    public void whenDeleteFirstOfTwoItemsThenTrackerHasOnlySecondItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.delete(tracker.getAll()[0].getId());
        Item[] expected = new Item[1];
        expected[0] = new Item("test2", "testDescription2", 124L);
        expected[0].setId(tracker.getAll()[0].getId());
        assertArrayEquals(expected, tracker.getAll());
    }

    @Test
    public void whenDeleteSecondOfTwoItemsThenTrackerHasOnlyFirstItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.delete(tracker.getAll()[1].getId());
        Item[] expected = new Item[1];
        expected[0] = new Item("test1", "testDescription1", 123L);
        expected[0].setId(tracker.getAll()[0].getId());
        assertArrayEquals(expected, tracker.getAll());
    }

    @Test
    public void whenGetAllItemsThenTrackerGivesNoAnyItems() {
        Tracker tracker = new Tracker();
        Item[] nothing = new Item[0];
        assertArrayEquals(nothing, tracker.getAll());
    }

    @Test
    public void whenGetAllItemsThenTrackerGivesOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescriptio1", 123L);
        tracker.add(item);
        Item[] expected = new Item[1];
        expected[0] = new Item("test1", "testDescriptio1", 123L);
        expected[0].setId(tracker.getAll()[0].getId());
        assertArrayEquals(expected, tracker.getAll());
    }

    @Test
    public void whenGetAllItemsThenTrackerGivesTwoItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        tracker.add(item1);
        tracker.add(item2);
        Item[] expected = new Item[2];
        expected[0] = new Item("test1", "testDescription1", 123L);
        expected[0].setId(tracker.getAll()[0].getId());
        expected[1] = new Item("test2", "testDescription2", 124L);
        expected[1].setId(tracker.getAll()[1].getId());
        assertArrayEquals(expected, tracker.getAll());
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
        Item[] expected = new Item[1];
        expected[0] = new Item("test2", "testDescription2", 124L);
        expected[0].setId(tracker.getAll()[1].getId());
        assertArrayEquals(expected, tracker.findByName("test2"));
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
        Item[] expected = new Item[0];
        assertArrayEquals(expected, tracker.findByName("qwerty"));
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
        Item[] expected = new Item[2];
        expected[0] = new Item("test", "testDescription1", 123L);
        expected[0].setId(tracker.getAll()[0].getId());
        expected[1] = new Item("test", "testDescription2", 124L);
        expected[1].setId(tracker.getAll()[1].getId());
        assertArrayEquals(expected, tracker.findByName("test"));
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
        Item[] expected = new Item[2];
        expected[0] = new Item("test", "testDescription2", 124L);
        expected[0].setId(tracker.getAll()[1].getId());
        expected[1] = new Item("test", "testDescription3", 125L);
        expected[1].setId(tracker.getAll()[2].getId());
        assertArrayEquals(expected, tracker.findByName("test"));
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
        Item[] expected = new Item[3];
        expected[0] = new Item("test", "testDescription1", 123L);
        expected[0].setId(tracker.getAll()[0].getId());
        expected[1] = new Item("test", "testDescription2", 124L);
        expected[1].setId(tracker.getAll()[1].getId());
        expected[2] = new Item("test", "testDescription3", 125L);
        expected[2].setId(tracker.getAll()[2].getId());
        assertArrayEquals(expected, tracker.findByName("test"));
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
        expected.setId(tracker.getAll()[1].getId());
        assertThat(tracker.findById(tracker.getAll()[1].getId()), is(expected));
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