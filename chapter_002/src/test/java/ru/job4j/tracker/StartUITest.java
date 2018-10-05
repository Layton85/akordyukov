package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

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
    /**
     * default system output - in console
     */
    private final PrintStream stdout = System.out;

    /**
     * buffer in memory for the output
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * The method reload system output to the variable out.
     * loadOutput() always executes before tests.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * The method changes output to the standart system output in console.
     * backOutput() always executes after tests.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test"));
    }

    @Test
    public void whenUserAskShowAllItemsAndTrackerHasNoAnyItemsThenTrackerShowMessage() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"1", "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator(), startUI.getMenuText(), System.lineSeparator() + startUI.getMenuText())
                                .add("------- show all items in tracker: -------")
                                .add("No any items in tracker")
                                .add("--------------------")
                                .toString()
                )
        );
    }

    @Test
    public void whenUserAskShowAllItemsThenTrackerShowOneItemWithoutComments() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(new String[]{"1", "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(startUI.getMenuText())
                                .append("------- show all items in tracker: -------")
                                .append(System.lineSeparator())
                                .append(item.toString())
                                .append(System.lineSeparator())
                                .append("--------------------")
                                .append(System.lineSeparator())
                                .append(startUI.getMenuText())
                                .toString()
                )
        );
    }

    @Test
    public void whenUserAskShowAllItemsThenTrackerShowOneItemWithComments() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L, new String[]{"comment1", "comment2"});
        tracker.add(item);
        Input input = new StubInput(new String[]{"1", "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(startUI.getMenuText())
                                .append("------- show all items in tracker: -------")
                                .append(System.lineSeparator())
                                .append(item.toString())
                                .append(System.lineSeparator())
                                .append("--------------------")
                                .append(System.lineSeparator())
                                .append(startUI.getMenuText())
                                .toString()
                )
        );
    }

    @Test
    public void whenUserAskShowAllItemsThenTrackerShowTwoItemsWithComments() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "desc1", 123L, new String[]{"comment11", "Comment12"});
        tracker.add(item1);
        Item item2 = new Item("test2", "desc2", 124L, new String[]{"comment21"});
        tracker.add(item2);
        Input input = new StubInput(new String[]{"1", "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(startUI.getMenuText())
                                .append("------- show all items in tracker: -------")
                                .append(System.lineSeparator())
                                .append(item1.toString())
                                .append(System.lineSeparator())
                                .append(item2.toString())
                                .append(System.lineSeparator())
                                .append("--------------------")
                                .append(System.lineSeparator())
                                .append(startUI.getMenuText())
                                .toString()
                )
        );
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
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
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
        Input input = new StubInput(new String[]{"3", items[0].getId(), "6"});
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
        Input input = new StubInput(new String[]{"3", items[1].getId(), "6"});
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
        Input input = new StubInput(new String[]{"3", items[2].getId(), "6"});
        new StartUI(input, tracker).init();
        assertArrayEquals(expected, tracker.getAll());
    }

    @Test
    public void whenFindByIdThenTrackerReturnsNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", "000", "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(startUI.getMenuText() + "------- find item by id: -------")
                                .add("incorrect item id")
                                .add("--------------------")
                                .add(startUI.getMenuText())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByIdThenTrackerReturnsItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", tracker.getAll()[0].getId(), "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(startUI.getMenuText() + "------- find item by id: -------")
                                .add(item.toString())
                                .add("--------------------")
                                .add(startUI.getMenuText())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenTrackerReturnsNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", "000", "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(startUI.getMenuText() + "------- find items by name: -------")
                                .add("items with this name was not found")
                                .add("--------------------")
                                .add(startUI.getMenuText())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenTrackerReturnsOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", "test1", "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(startUI.getMenuText() + "------- find items by name: -------")
                                .add(item.toString())
                                .add("--------------------")
                                .add(startUI.getMenuText())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenTrackerReturnsTwoItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test", "desc1", 123L);
        tracker.add(item1);
        Item item2 = new Item("test", "desc2", 124L);
        tracker.add(item2);

        Input input = new StubInput(new String[]{"5", "test", "6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(startUI.getMenuText() + "------- find items by name: -------")
                                .add(item1.toString())
                                .add(item2.toString())
                                .add("--------------------")
                                .add(startUI.getMenuText())
                                .toString()
                )
        );
    }

    @Test
    public void whenExitStartUIReturnsOnlyMenu() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"6"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(
                new String(out.toByteArray()),
                is(new String(startUI.getMenuText()))
        );
    }
}
