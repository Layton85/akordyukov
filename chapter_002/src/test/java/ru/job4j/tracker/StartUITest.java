package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * StartUITest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test"));
    }

    @Test
    public void whenEditThenTrackerHasEditedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteOnlyItemThenTrackerHasNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test", "desc"));
        Input input = new StubInput(new String[] {"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        Item[] empty = new Item[0];
        assertArrayEquals(empty, tracker.getAll());
    }

    @Test
    public void whenDeleteFirstOfThreeItemsThenTrackerHasSecondAndThirdItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        Item[] items = new Item[amount];
        for (int i = 0; i < amount; i++) {
            items[i] = new Item("test" + String.valueOf(i + 1), "testDescription" + String.valueOf(i + 1),
                    123L + i);
            tracker.add(items[i]);
        }
        Item[] expected = new Item[amount - 1];
        for (int i = 0; i < amount - 1; i++) {
            expected[i] = new Item("test" + String.valueOf(i + 2), "testDescription" + String.valueOf(i + 2),
                    123L + i + 1);
            expected[i].setId(tracker.getAll()[i + 1].getId());
        }
        Input input = new StubInput(new String[] {"3", items[0].getId(), "6"});
        new StartUI(input, tracker).init();
        assertArrayEquals(expected, tracker.getAll());
    }

    @Test
    public void whenDeleteSecondOfThreeItemsThenTrackerHasFirstAndThirdItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        Item[] items = new Item[amount];
        for (int i = 0; i < amount; i++) {
            items[i] = new Item("test" + String.valueOf(i + 1), "testDescription" + String.valueOf(i + 1),
                    123L + i);
            tracker.add(items[i]);
        }
        Item[] expected = new Item[amount - 1];
        for (int i = 0; i < amount - 1; i++) {
            expected[i] = new Item("test" + String.valueOf(2 * i + 1), "testDescription" + String.valueOf(2 * i + 1),
                    123L + 2 * i);
            expected[i].setId(tracker.getAll()[2 * i].getId());
        }
        Input input = new StubInput(new String[] {"3", items[1].getId(), "6"});
        new StartUI(input, tracker).init();
        assertArrayEquals(expected, tracker.getAll());
    }

    @Test
    public void whenDeleteThirdOfThreeItemsThenTrackerHasFirstAndSecondItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        Item[] items = new Item[amount];
        for (int i = 0; i < amount; i++) {
            items[i] = new Item("test" + String.valueOf(i + 1), "testDescription" + String.valueOf(i + 1),
                    123L + i);
            tracker.add(items[i]);
        }
        Item[] expected = new Item[amount - 1];
        for (int i = 0; i < amount - 1; i++) {
            expected[i] = new Item("test" + String.valueOf(i + 1), "testDescription" + String.valueOf(i + 1),
                    123L + i);
            expected[i].setId(tracker.getAll()[i].getId());
        }
        Input input = new StubInput(new String[] {"3", items[2].getId(), "6"});
        new StartUI(input, tracker).init();
        assertArrayEquals(expected, tracker.getAll());
    }
}
