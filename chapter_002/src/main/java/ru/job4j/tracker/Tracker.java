package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private final List<Item> items = new ArrayList<>();
    /** Random Object for generating random numbers for id. */
    private static final Random RN = new Random();

    /**
     * The method adds new item into the item storage.
     * @param item - Item Object for adding
     * @return added Item Object from the storage
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
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
     * @return result of function actions: true - item replaced successfully, false - item was not replaced.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int editedPos = this.findPositionById(id);
        if (editedPos >= 0) {
            item.setId(this.items.get(editedPos).getId());
            this.items.set(editedPos, item);
            result = true;
        }
        return result;
    }

    /**
     * The method deletes item from the storage.
     * The method records the last non-null item in the storage in place of the deleted item.
     * Then the method sets null instead of the last non-null item and reduces position of storage on 1.
     * If the storage doesn`t consist item with id received as parameter the method do nothing.
     * @param id - id of the item to deleting.
     * @return result of deleting item: true - item was deleted, false - item was not deleted.
     */
    public boolean delete(String id) {
        boolean result = false;
        int delPos = this.findPositionById(id);
        if ((delPos >= 0)) {
            this.items.remove(delPos);
            result = true;
        }
        return result;
    }

    /**
     * The method gets all items from the storage.
     * @return array of all items from the storage.
     */
    public List<Item> getAll() {
        return this.items;
    }

    /**
     * The method finds an item from storage by name field.
     * @param key - the name field with wich items are looked for.
     * @return array of all items from the storage with name like a key.
     */
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                list.add(item);
            }
        }
        return list;
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
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = this.items.indexOf(item);
                break;
            }
        }
        return result;
    }
}
