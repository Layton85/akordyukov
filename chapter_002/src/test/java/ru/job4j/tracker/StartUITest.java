package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{StartUI.ADD, "test", "desc", StartUI.EXIT});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test"));
    }

    @Test
    public void whenEditThenTrackerHasEditedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test", "desc"));
        Input input = new StubInput(new String[]{StartUI.EDIT, item.getId(), "test replace", "заменили заявку", StartUI.EXIT});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteOnlyItemThenTrackerHasNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test", "desc"));
        Input input = new StubInput(new String[] {StartUI.DELETE, item.getId(), StartUI.EXIT});
        new StartUI(input, tracker).init();
        Item[] empty = new Item[0];
        assertArrayEquals(empty, tracker.getAll());
    }

    @Test
    public void whenDeleteFirstOfThreeItemsThenTrackerHasSecondAndThirdItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        Item[] items = new Item[amount];
        for(int i = 0; i < amount; i++) {
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
        Input input = new StubInput(new String[] {StartUI.DELETE, items[0].getId(), StartUI.EXIT});
        new StartUI(input, tracker).init();
        assertArrayEquals(expected, tracker.getAll());
    }

    @Test
    public void whenDeleteSecondOfThreeItemsThenTrackerHasFirstAndThirdItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        Item[] items = new Item[amount];
        for(int i = 0; i < amount; i++) {
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
        Input input = new StubInput(new String[] {StartUI.DELETE, items[1].getId(), StartUI.EXIT});
        new StartUI(input, tracker).init();
        assertArrayEquals(expected, tracker.getAll());
    }

    @Test
    public void whenDeleteThirdOfThreeItemsThenTrackerHasFirstAndSecondItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        Item[] items = new Item[amount];
        for(int i = 0; i < amount; i++) {
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
        Input input = new StubInput(new String[] {StartUI.DELETE, items[2].getId(), StartUI.EXIT});
        new StartUI(input, tracker).init();
        assertArrayEquals(expected, tracker.getAll());
    }
}
