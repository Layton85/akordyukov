package ru.job4j.tracker;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * MenuTracker - The class ensuring functioning of all menu points.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {
    /**
     * @param input - holds the reference to the implementing input interface.
     */
    private Input input;

    /**
     * @param tracker - holds the reference to the items storage.
     */
    private Tracker tracker;

    /**
     * @param actions - holds the reference to the List of menu actions.
     */
    private List<UserAction> actions = new ArrayList<>();

    /** output action */
    private final Consumer<String> output;

    /**
     * Constructor.
     *
     * @param input - interface Input.
     * @param tracker - the storage Tracker.
     */
    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Get-method for the actions length.
     * @return - the size of the actions list.
     */
    public int getActionsLength() {
        return actions.size();
    }

    /**
     * The method fill in the List of menu actions.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(this.new AddItem(0, "Add new item"));
        this.actions.add(new ShowItems(1, "Show all items"));
        this.actions.add(new MenuTracker.EditItem(2, "Edit item"));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
        this.actions.add(new ExitProgram(6, "Exit Program", ui));
    }

    /**
     * The method chooses the correct action depending on the specified key
     * @param key - the key of the menu point
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker, this.output);
    }

    /** The method showes menu */
    public void show() {
        this.output.accept("Menu:");
        for (UserAction action : this.actions) {
            if (action != null) {
                this.output.accept(action.info());
            }
        }
    }

    /**
     * Get-Method
     * @return Menu String.
     */
    public String get() {
        StringBuilder result = new StringBuilder("Menu:");
        result.append(System.lineSeparator());
        for (UserAction action : this.actions) {
            if (action != null) {
                result.append(action.info());
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    /**
     * AddItem - Internal class providing functioning of menu point 0.Add New Item.
     * @author Alexander Kordyukov (alex-programm@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private class AddItem extends BaseAction {
        /**
         * Constructor
         * @param key - key of this menu option
         * @param name - information of this menu option
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Override method implements method void execute(Input input, Tracker tracker) from the interface UserAction.
         * @param input - interface Input.
         * @param tracker - storage object Tracker.
         * @param output - output action.
         */
        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            output.accept("------------ Adding new item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept("------------ New Item with Id : " + item.getId());
            output.accept("------------ New Item with Name : " + item.getName());
            output.accept("------------ New Item with Description : " + item.getDesc());
        }
    }

    /**
     * ShowItems - Internal class providing functioning of menu point 1.Show all items.
     * @author Alexander Kordyukov (alex-programm@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private class ShowItems extends BaseAction {
        /**
         * Constructor
         * @param key - key of this menu option
         * @param name - information of this menu option
         */
        public ShowItems(int key, String name) {
            super(key, name);
        }

        /**
         * Override method implements method void execute(Input input, Tracker tracker) from the interface UserAction.
         * @param input - interface Input.
         * @param tracker - storage object Tracker.
         * @param output - output action.
         */
        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            output.accept("------- show all items in tracker: -------");
            List<Item> list = tracker.getAll();
            if (list.isEmpty()) {
                output.accept("No any items in tracker");
            } else {
                for (Item item : list) {
                    output.accept(item.toString());
                }
            }
            output.accept("--------------------");
        }
    }

    /**
     * EditItem - Internal static class providing functioning of menu point 2.Edit item.
     * @author Alexander Kordyukov (alex-programm@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private static class EditItem extends BaseAction {
        /**
         * Constructor
         * @param key - key of this menu option
         * @param name - information of this menu option
         */
        public EditItem(int key, String name) {
            super(key, name);
        }

        /**
         * Override method implements method void execute(Input input, Tracker tracker) from the interface UserAction.
         * @param input - interface Input.
         * @param tracker - storage object Tracker.
         * @param output - output action.
         */
        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            output.accept("------- edit item: -------");
            String id = input.ask("enter item id: ");
            if (id.isEmpty()) {
                output.accept("incorrect item id");
            } else {
                String name = input.ask("enter new item name: ");
                String description = input.ask("enter item description: ");
                Item item = new Item(name, description);
                if (tracker.replace(id, item)) {
                    output.accept("successful edit of item");
                } else {
                    output.accept("error: item was not edited");
                }
            }
            output.accept("--------------------");
        }
    }

    /**
     * DeleteItem - Internal static class providing functioning of menu point 3.Delete item.
     * @author Alexander Kordyukov (alex-programm@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private static class DeleteItem extends BaseAction {
        /**
         * Constructor
         * @param key - key of this menu option
         * @param name - information of this menu option
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Override method implements method void execute(Input input, Tracker tracker) from the interface UserAction.
         * @param input - interface Input.
         * @param tracker - storage object Tracker.
         * @param output - output action.
         */
        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            output.accept("------- delete item: -------");
            String id = input.ask("enter item id: ");
            if (id.isEmpty()) {
                output.accept("incorrect item id");
            } else {
                if (tracker.delete(id)) {
                    output.accept("item was deleted successfully");
                } else {
                    output.accept("error: item was not delited");
                }
            }
            output.accept("--------------------");
        }
    }
}

/**
 * FindItemById - class providing functioning of menu point 4.Find item by id.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class FindItemById extends BaseAction {
    /**
     * Constructor
     * @param key - key of this menu option
     * @param name - information of this menu option
     */
    public FindItemById(int key, String name) {
        super(key, name);
    }

    /**
     * Override method implements method void execute(Input input, Tracker tracker) from the interface UserAction.
     * @param input - interface Input.
     * @param tracker - storage object Tracker.
     * @param output - output action.
     */
    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        output.accept("------- find item by id: -------");
        String id = input.ask("enter item id: ");
        Item item = tracker.findById(id);
        if (item == null) {
            output.accept("incorrect item id");
        } else {
            output.accept(item.toString());
        }
        output.accept("--------------------");
    }
}

/**
 * FindItemsByName - class providing functioning of menu point 5.Find items by name.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class FindItemsByName extends BaseAction {
    /**
     * Constructor
     * @param key - key of this menu option
     * @param name - information of this menu option
     */
    public FindItemsByName(int key, String name) {
        super(key, name);
    }

    /**
     * Override method implements method void execute(Input input, Tracker tracker) from the interface UserAction.
     * @param input - interface Input.
     * @param tracker - storage object Tracker.
     * @param output - output action.
     */
    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        output.accept("------- find items by name: -------");
        String name = input.ask("enter item name: ");
        List<Item> list = tracker.findByName(name);
        if (list.isEmpty()) {
            output.accept("items with this name was not found");
        } else {
            for (Item item : list) {
                output.accept(item.toString());
            }
        }
        output.accept("--------------------");
    }
}

/**
 * ExitProgram - class providing functioning of menu point 6.ExitProgram.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class ExitProgram extends BaseAction {
    /** reference to the main program module */
    private final StartUI ui;

    /**
     * Constructor
     * @param key - key of this menu option
     * @param name - information of this menu option
     * @param ui - reference to the main program module
     */
    public ExitProgram(int key, String name, StartUI ui) {
        super(key, name);
        this.ui = ui;
    }

    /**
     * Override method implements method void execute(Input input, Tracker tracker) from the interface UserAction.
     * @param input - interface Input.
     * @param tracker - storage object Tracker.
     * @param output - output action.
     */
    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        if ("y".equals(input.ask("Exit?(y): "))) {
            this.ui.stop();
        }
    }
}