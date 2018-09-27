package ru.job4j.tracker;

/**
 * StartUI - main class with entry point in programm.
 * StartUI provides console user interface for tracking system.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /** menu item "add new item in tracker" */
    static final String ADD = "0";

    /** menu item "show all items in tracker" */
    static final String SHOW = "1";

    /** menu item "edit some item in tracker" */
    static final String EDIT = "2";

    /** menu item "delete some item from tracker" */
    static final String DELETE = "3";

    /** menu item "find some item in tracker using id as a key" */
    static final String FINDID = "4";

    /** menu item "find some items in tracker using name as a key" */
    static final String FINDNAME = "5";

    /** menu item "exit programm" */
    static final String EXIT = "6";

    /** reference to the I/O interface */
    private final Input input;

    /** reference to the Tracker object */
    private final Tracker tracker;

    /**
     * Constructor.
     * @param input - using I/O class
     * @param tracker - connected Tracker object
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /** function initializing programm */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDID.equals(answer)) {
                this.findById();
            } else if (FINDNAME.equals(answer)) {
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /** function shows menu items in console to user */
    private void showMenu() {
        String str = "Menu:" + System.lineSeparator()
                        + "0. Add new item" + System.lineSeparator()
                        + "1. Show all items" + System.lineSeparator()
                        + "2. Edit item" + System.lineSeparator()
                        + "3. Delete item" + System.lineSeparator()
                        + "4. Find item by id" + System.lineSeparator()
                        + "5. Find items by name" + System.lineSeparator()
                        + "6. Exit Programm" + System.lineSeparator();
        System.out.print(str);
    }

    /** function adds new item in tracker */
    private void createItem() {
        System.out.println("-------- Adding new item -------");
        String name = this.input.ask("enter item name: ");
        String desc = this.input.ask("enter item description: ");
        Item item = new Item(name, desc);
        System.out.println("new item is: " + System.lineSeparator() + this.tracker.add(item).toString());
        System.out.println("--------------------");
    }

    /** function shows all items in tracker */
    private void showAllItems() {
        System.out.println("------- show all items in tracker: -------");
        Item[] arr = this.tracker.getAll();
        if (arr.length == 0) {
            System.out.println("No any items in tracker");
        } else {
            for (Item item : arr) {
                System.out.println(item.toString());
            }
        }
        System.out.println("--------------------");
    }

    /** function edits some item in tracker */
    private void editItem() {
        System.out.println("------- edit item: -------");
        String id = this.input.ask("enter item id: ");
        if (id.isEmpty()) {
            System.out.println("incorrect item id");
        } else {
            String name = this.input.ask("enter new item name: ");
            String description = this.input.ask("enter item description: ");
            Item item = new Item(name, description);
            if (this.tracker.replace(id, item)) {
                System.out.println("successful edit of item");
            } else {
                System.out.println("error: item was not edited");
            }
        }
        System.out.println("--------------------");
    }

    /** function deletes some item from tracker */
    private void deleteItem() {
        System.out.println("------- delete item: -------");
        String id = this.input.ask("enter item id: ");
        if (id.isEmpty()) {
            System.out.println("incorrect item id");
        } else {
            if (this.tracker.delete(id)) {
                System.out.println("item was deleted successfully");
            } else {
                System.out.println("error: item was not delited");
            }
        }
        System.out.println("--------------------");
    }

    /** function finds some item in tracker using id as a key */
    private void findById() {
        System.out.println("------- find item by id: -------");
        String id = this.input.ask("enter item id: ");
        Item item = this.tracker.findById(id);
        if (item == null) {
            System.out.println("incorrect item id");
        } else {
            System.out.println(item.toString());
        }
        System.out.println("--------------------");
    }

    /** function finds some items in tracker using name as a key */
    private void findItemsByName() {
        System.out.println("------- find items by name: -------");
        String name = this.input.ask("enter item name: ");
        Item[] arr = this.tracker.findByName(name);
        if (arr.length == 0) {
            System.out.println("items with this name was not found");
        } else {
            for (Item item : arr) {
                System.out.println(item.toString());
            }
        }
        System.out.println("--------------------");
    }

    /* main function - entry point in programm */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}