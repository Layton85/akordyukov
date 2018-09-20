package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Tracker - class for work with items (holding, adding, redacting, deleting, finding).
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /** storage of items */
    private final Item[] items = new Item[100];
    /** current position of items counter - show current number of non-null Items in the storage */
    private int position = 0;
    /** Random Object for generating random numbers for id. */
    private static final Random RN = new Random();

    /**
     * The method adds new item into the item storage.
     * @param item - Item Object for adding
     * @return added Item Object from the storage
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * The additional auxilliary method that helps generate id for the item.
     * @return generated id String.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * The method edits the item that already exist in the storage.
     * @param id - id of the edited item.
     * @param item - Item Object which replaces the edited item.
     */
    public void replace(String id, Item item) {
        int editedPos = this.findPositionById(id);
        if (editedPos >= 0) {
            item.setId(this.items[editedPos].getId());
            this.items[editedPos] = item;
        }
    }

    /**
     * The method deletes item from the storage.
     * The method records the last non-null item in the storage in place of the deleted item.
     * Then the method sets null instead of the last non-null item and reduces position of storage on 1.
     * If the storage doesn`t consist item with id received as parameter the method do nothing.
     * @param id - id of the item to deleting.
     */
    public void delete(String id) {
        int delPos = this.findPositionById(id);
        if ((delPos >= 0) && (delPos <= this.position - 1)) {
            if (delPos != this.position - 1) {
                System.arraycopy(this.items, delPos + 1, this.items, delPos, this.position - (delPos + 1));
            }
            this.items[this.position - 1] = null;
            this.position--;
        }
    }

    /**
     * The method gets all items from the storage.
     * @return array of all items from the storage.
     */
    public Item[] getAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * The method finds an item from storage by name field.
     * @param key - the name field with wich items are looked for.
     * @return array of all items from the storage with name like a key.
     */
    public Item[] findByName(String key) {
        Item[] arr = new Item[100];
        int cnt = 0;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getName().equals(key)) {
                arr[cnt++] = items[i];
            }
        }
        return Arrays.copyOf(arr, cnt);
    }

    /**
     * The method finds an item from storage by id.
     * @param id - the id with wich item is looked for.
     * @return Item Object from the storage that have a required id.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * The additional auxilliary method that helps find position of item in the storage.
     * @param id - id of item that is looked for.
     * @return position of the item in storage if it include the item with this id or -1 if the storage do not include it.
     */
    private int findPositionById(String id) {
        int result = -1;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
