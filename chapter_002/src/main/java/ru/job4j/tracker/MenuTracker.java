package ru.job4j.tracker;

import java.util.List;
import java.util.ArrayList;

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

    /**
     * Constructor.
     *
     * @param input - interface Input.
     * @param tracker - the storage Tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * The method fill in the List of menu actions.
     */
    public void fillActions() {
        this.actions.add(this.new AddItem(0, "Add new item"));
        this.actions.add(new ShowItems(1, "Show all items"));
        this.actions.add(new MenuTracker.EditItem(2, "Edit item"));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
        this.actions.add(new ExitProgram(6, "Exit Program"));
    }

    /**
     * The method chooses the correct action depending on the specified key
     * @param menuCode - the key of the menu point
     */
    public String select(String menuCode) {
        Integer key = Integer.parseInt(menuCode);
        return this.actions.get(key).execute(this.input, this.tracker);
    }

    /** The method showes menu */
    public void show() {
        System.out.println("Menu:");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
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
    private class AddItem implements UserAction {
        /** key of this menu option */
        private int key;
        /** information of this menu option */
        private String info;

        /**
         * Constructor
         * @param key - key of this menu option
         * @param info - information of this menu option
         */
        public AddItem(int key, String info) {
            this.key = key;
            this.info = info;
        }

        /**
         * Override method implements method int key() from the interface UserAction.
         * @return key of this menu option.
         */
        @Override
        public int key() {
            return this.key;
        }

        /**
         * Override method implements method String execute(Input input, Tracker tracker) from the interface UserAction.
         * @param input - interface Input.
         * @param tracker - storage object Tracker.
         * @return - the message generated during execution.
         */
        @Override
        public String execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ New Item with Id : " + item.getId());
            System.out.println("------------ New Item with Name : " + item.getName());
            System.out.println("------------ New Item with Description : " + item.getDesc());
            return "";
        }

        /**
         * Override method implements method String info() from the interface UserAction.
         * @return - information of this menu option.
         */
        @Override
        public String info() {
            return String.format("%s.%s", this.key, this.info);
        }
    }

    /**
     * ShowItems - Internal class providing functioning of menu point 1.Show all items.
     * @author Alexander Kordyukov (alex-programm@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private class ShowItems implements UserAction {
        /** key of this menu option */
        private int key;
        /** information of this menu option */
        private String info;

        /**
         * Constructor
         * @param key - key of this menu option
         * @param info - information of this menu option
         */
        public ShowItems(int key, String info) {
            this.key = key;
            this.info = info;
        }

        /**
         * Override method implements method int key() from the interface UserAction.
         * @return key of this menu option.
         */
        @Override
        public int key() {
            return this.key;
        }

        /**
         * Override method implements method String execute(Input input, Tracker tracker) from the interface UserAction.
         * @param input - interface Input.
         * @param tracker - storage object Tracker.
         * @return - the message generated during execution.
         */
        @Override
        public String execute(Input input, Tracker tracker) {
            System.out.println("------- show all items in tracker: -------");
            Item[] arr = tracker.getAll();
            if (arr.length == 0) {
                System.out.println("No any items in tracker");
            } else {
                for (Item item : arr) {
                    System.out.println(item.toString());
                }
            }
            System.out.println("--------------------");
            return "";
        }

        /**
         * Override method implements method String info() from the interface UserAction.
         * @return - information of this menu option.
         */
        @Override
        public String info() {
            return String.format("%s.%s", this.key, this.info);
        }
    }

    /**
     * EditItem - Internal static class providing functioning of menu point 2.Edit item.
     * @author Alexander Kordyukov (alex-programm@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private static class EditItem implements UserAction {
        /** key of this menu option */
        private int key;
        /** information of this menu option */
        private String info;

        /**
         * Constructor
         * @param key - key of this menu option
         * @param info - information of this menu option
         */
        public EditItem(int key, String info) {
            this.key = key;
            this.info = info;
        }

        /**
         * Override method implements method int key() from the interface UserAction.
         * @return key of this menu option.
         */
        @Override
        public int key() {
            return this.key;
        }

        /**
         * Override method implements method String execute(Input input, Tracker tracker) from the interface UserAction.
         * @param input - interface Input.
         * @param tracker - storage object Tracker.
         * @return - the message generated during execution.
         */
        @Override
        public String execute(Input input, Tracker tracker) {
            System.out.println("------- edit item: -------");
            String id = input.ask("enter item id: ");
            if (id.isEmpty()) {
                System.out.println("incorrect item id");
            } else {
                String name = input.ask("enter new item name: ");
                String description = input.ask("enter item description: ");
                Item item = new Item(name, description);
                if (tracker.replace(id, item)) {
                    System.out.println("successful edit of item");
                } else {
                    System.out.println("error: item was not edited");
                }
            }
            System.out.println("--------------------");
            return "";
        }

        /**
         * Override method implements method String info() from the interface UserAction.
         * @return - information of this menu option.
         */
        @Override
        public String info() {
            return String.format("%s.%s", this.key, this.info);
        }
    }

    /**
     * DeleteItem - Internal static class providing functioning of menu point 3.Delete item.
     * @author Alexander Kordyukov (alex-programm@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private static class DeleteItem implements UserAction {
        /** key of this menu option */
        private int key;
        /** information of this menu option */
        private String info;

        /**
         * Constructor
         * @param key - key of this menu option
         * @param info - information of this menu option
         */
        public DeleteItem(int key, String info) {
            this.key = key;
            this.info = info;
        }

        /**
         * Override method implements method int key() from the interface UserAction.
         * @return key of this menu option.
         */
        @Override
        public int key() {
            return this.key;
        }

        /**
         * Override method implements method String execute(Input input, Tracker tracker) from the interface UserAction.
         * @param input - interface Input.
         * @param tracker - storage object Tracker.
         * @return - the message generated during execution.
         */
        @Override
        public String execute(Input input, Tracker tracker) {
            System.out.println("------- delete item: -------");
            String id = input.ask("enter item id: ");
            if (id.isEmpty()) {
                System.out.println("incorrect item id");
            } else {
                if (tracker.delete(id)) {
                    System.out.println("item was deleted successfully");
                } else {
                    System.out.println("error: item was not delited");
                }
            }
            System.out.println("--------------------");
            return "";
        }

        /**
         * Override method implements method String info() from the interface UserAction.
         * @return - information of this menu option.
         */
        @Override
        public String info() {
            return String.format("%s.%s", this.key, this.info);
        }
    }

}

/**
 * FindItemById - class providing functioning of menu point 4.Find item by id.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class FindItemById implements UserAction {
    /** key of this menu option */
    private int key;
    /** information of this menu option */
    private String info;

    /**
     * Constructor
     * @param key - key of this menu option
     * @param info - information of this menu option
     */
    public FindItemById(int key, String info) {
        this.key = key;
        this.info = info;
    }

    /**
     * Override method implements method int key() from the interface UserAction.
     * @return key of this menu option.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Override method implements method String execute(Input input, Tracker tracker) from the interface UserAction.
     * @param input - interface Input.
     * @param tracker - storage object Tracker.
     * @return - the message generated during execution.
     */
    @Override
    public String execute(Input input, Tracker tracker) {
        System.out.println("------- find item by id: -------");
        String id = input.ask("enter item id: ");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("incorrect item id");
        } else {
            System.out.println(item.toString());
        }
        System.out.println("--------------------");
        return "";
    }

    /**
     * Override method implements method String info() from the interface UserAction.
     * @return - information of this menu option.
     */
    @Override
    public String info() {
        return String.format("%s.%s", this.key, this.info);
    }
}

/**
 * FindItemsByName - class providing functioning of menu point 5.Find items by name.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class FindItemsByName implements UserAction {
    /** key of this menu option */
    private int key;
    /** information of this menu option */
    private String info;

    /**
     * Constructor
     * @param key - key of this menu option
     * @param info - information of this menu option
     */
    public FindItemsByName(int key, String info) {
        this.key = key;
        this.info = info;
    }

    /**
     * Override method implements method int key() from the interface UserAction.
     * @return key of this menu option.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Override method implements method String execute(Input input, Tracker tracker) from the interface UserAction.
     * @param input - interface Input.
     * @param tracker - storage object Tracker.
     * @return - the message generated during execution.
     */
    @Override
    public String execute(Input input, Tracker tracker) {
        System.out.println("------- find items by name: -------");
        String name = input.ask("enter item name: ");
        Item[] arr = tracker.findByName(name);
        if (arr.length == 0) {
            System.out.println("items with this name was not found");
        } else {
            for (Item item : arr) {
                System.out.println(item.toString());
            }
        }
        System.out.println("--------------------");
        return "";
    }

    /**
     * Override method implements method String info() from the interface UserAction.
     * @return - information of this menu option.
     */
    @Override
    public String info() {
        return String.format("%s.%s", this.key, this.info);
    }
}

/**
 * ExitProgram - class providing functioning of menu point 6.ExitProgram.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class ExitProgram implements UserAction {
    /** key of this menu option */
    private int key;
    /** information of this menu option */
    private String info;

    /**
     * Constructor
     * @param key - key of this menu option
     * @param info - information of this menu option
     */
    public ExitProgram(int key, String info) {
        this.key = key;
        this.info = info;
    }

    /**
     * Override method implements method int key() from the interface UserAction.
     * @return key of this menu option.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Override method implements method String execute(Input input, Tracker tracker) from the interface UserAction.
     * @param input - interface Input.
     * @param tracker - storage object Tracker.
     * @return - the message generated during execution.
     */
    @Override
    public String execute(Input input, Tracker tracker) {
        return input.ask("Exit?(y): ");
    }

    /**
     * Override method implements method String info() from the interface UserAction.
     * @return - information of this menu option.
     */
    @Override
    public String info() {
        return String.format("%s.%s", this.key, this.info);
    }
}