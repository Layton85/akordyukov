package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

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

    /** output using functional interface */
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return new String(out.toByteArray());
        }
    };

    /**
     * The method reload system output to the variable out.
     * loadOutput() always executes before tests.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * The method changes output to the standard system output in console.
     * backOutput() always executes after tests.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(Arrays.asList("0", "test", "desc", "6", "y"));
        new StartUI(input, tracker, this.output).init();
        assertThat(tracker.getAll().get(0).getName(), is("test"));
    }

    @Test
    public void whenUserAskShowAllItemsAndTrackerHasNoAnyItemsThenTrackerShowMessage() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(Arrays.asList("1", "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringJoiner(System.lineSeparator(), menu.get(), System.lineSeparator() + menu.get())
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
        Input input = new StubInput(Arrays.asList("1", "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append(menu.get())
                                .append("------- show all items in tracker: -------")
                                .append(System.lineSeparator())
                                .append(item.toString())
                                .append(System.lineSeparator())
                                .append("--------------------")
                                .append(System.lineSeparator())
                                .append(menu.get())
                                .toString()
                )
        );
    }

    @Test
    public void whenUserAskShowAllItemsThenTrackerShowOneItemWithComments() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L, Arrays.asList("comment1", "comment2"));
        tracker.add(item);
        Input input = new StubInput(Arrays.asList("1", "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append(menu.get())
                                .append("------- show all items in tracker: -------")
                                .append(System.lineSeparator())
                                .append(item.toString())
                                .append(System.lineSeparator())
                                .append("--------------------")
                                .append(System.lineSeparator())
                                .append(menu.get())
                                .toString()
                )
        );
    }

    @Test
    public void whenUserAskShowAllItemsThenTrackerShowTwoItemsWithComments() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "desc1", 123L, Arrays.asList("comment11", "Comment12"));
        tracker.add(item1);
        Item item2 = new Item("test2", "desc2", 124L, Arrays.asList("comment21"));
        tracker.add(item2);
        Input input = new StubInput(Arrays.asList("1", "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append(menu.get())
                                .append("------- show all items in tracker: -------")
                                .append(System.lineSeparator())
                                .append(item1.toString())
                                .append(System.lineSeparator())
                                .append(item2.toString())
                                .append(System.lineSeparator())
                                .append("--------------------")
                                .append(System.lineSeparator())
                                .append(menu.get())
                                .toString()
                )
        );
    }

    @Test
    public void whenEditThenTrackerHasEditedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test", "desc"));
        Input input = new StubInput(Arrays.asList("2", item.getId(), "test replace", "заменили заявку", "6", "y"));
        new StartUI(input, tracker, this.output).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteOnlyItemThenTrackerHasNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test", "desc"));
        Input input = new StubInput(Arrays.asList("3", item.getId(), "6", "y"));
        new StartUI(input, tracker, this.output).init();
        List<Item> empty = new ArrayList<>();
        assertThat(empty.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenDeleteFirstOfThreeItemsThenTrackerHasSecondAndThirdItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            items.add(new Item("test" + String.valueOf(i + 1), "testDescription" + String.valueOf(i + 1), 123L + i));
            tracker.add(items.get(i));
        }
        List<Item> expected = new ArrayList<>();
        for (int i = 0; i < amount - 1; i++) {
            expected.add(new Item("test" + String.valueOf(i + 2), "testDescription" + String.valueOf(i + 2), 123L + i + 1));
            expected.get(i).setId(tracker.getAll().get(i + 1).getId());
        }
        Input input = new StubInput(Arrays.asList("3", items.get(0).getId(), "6", "y"));
        new StartUI(input, tracker, this.output).init();
        assertThat(expected.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenDeleteSecondOfThreeItemsThenTrackerHasFirstAndThirdItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            items.add(new Item("test" + String.valueOf(i + 1), "testDescription" + String.valueOf(i + 1),
                    123L + i));
            tracker.add(items.get(i));
        }
        List<Item> expected = new ArrayList<>();
        for (int i = 0; i < amount - 1; i++) {
            expected.add(new Item("test" + String.valueOf(2 * i + 1), "testDescription" + String.valueOf(2 * i + 1),
                    123L + 2 * i));
            expected.get(i).setId(tracker.getAll().get(2 * i).getId());
        }
        Input input = new StubInput(Arrays.asList("3", items.get(1).getId(), "6", "y"));
        new StartUI(input, tracker, this.output).init();
        assertThat(expected.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenDeleteThirdOfThreeItemsThenTrackerHasFirstAndSecondItems() {
        Tracker tracker = new Tracker();
        final int amount = 3;
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            items.add(new Item("test" + String.valueOf(i + 1), "testDescription" + String.valueOf(i + 1),
                    123L + i));
            tracker.add(items.get(i));
        }
        List<Item> expected = new ArrayList<>();
        for (int i = 0; i < amount - 1; i++) {
            expected.add(new Item("test" + String.valueOf(i + 1), "testDescription" + String.valueOf(i + 1),
                    123L + i));
            expected.get(i).setId(tracker.getAll().get(i).getId());
        }
        Input input = new StubInput(Arrays.asList("3", items.get(2).getId(), "6", "y"));
        new StartUI(input, tracker, this.output).init();
        assertThat(expected.equals(tracker.getAll()), is(true));
    }

    @Test
    public void whenFindByIdThenTrackerReturnsNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(Arrays.asList("4", "000", "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu.get() + "------- find item by id: -------")
                                .add("incorrect item id")
                                .add("--------------------")
                                .add(menu.get())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByIdThenTrackerReturnsItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(Arrays.asList("4", tracker.getAll().get(0).getId(), "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu.get() + "------- find item by id: -------")
                                .add(item.toString())
                                .add("--------------------")
                                .add(menu.get())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenTrackerReturnsNoAnyItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(Arrays.asList("5", "000", "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu.get() + "------- find items by name: -------")
                                .add("items with this name was not found")
                                .add("--------------------")
                                .add(menu.get())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenTrackerReturnsOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Input input = new StubInput(Arrays.asList("5", "test1", "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu.get() + "------- find items by name: -------")
                                .add(item.toString())
                                .add("--------------------")
                                .add(menu.get())
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

        Input input = new StubInput(Arrays.asList("5", "test", "6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu.get() + "------- find items by name: -------")
                                .add(item1.toString())
                                .add(item2.toString())
                                .add("--------------------")
                                .add(menu.get())
                                .toString()
                )
        );
    }

    @Test
    public void whenExitStartUIReturnsOnlyMenu() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(Arrays.asList("6", "y"));
        StartUI startUI = new StartUI(input, tracker, this.output);
        startUI.init();
        MenuTracker menu = new MenuTracker(input, tracker, this.output);
        menu.fillActions(startUI);
        assertThat(
                this.output.toString(),
                is(new String(menu.get()))
        );
    }
}
