package ru.job4j.generic;

import java.util.Iterator;

/**
 * AbstractStore - abstract class which describe behavior of abstract store.
 * AbstractStore implements Store interface.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /** Store of elements. */
    protected SimpleArray elements;

    /**
     * Constructor.
     * @param size - store size.
     */
    protected AbstractStore(int size) {
        this.elements = new SimpleArray<>(size);
    }

    /**
     * Override method for adding elements to the store.
     * @param model - element to add.
     */
    @Override
    public void add(T model) {
        this.elements.add(model);
    }

    /**
     * Override method for replacing elements.
     * @param id - id of element which should be replaced.
     * @param model - new element.
     * @return - boolean value:
     *              true - if element was successfully replaced;
     *              false - otherwise.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        Integer position = this.findPositionById(id);
        if (position != null) {
            this.elements.set(position, model);
            result = true;
        }
        return result;
    }

    /**
     * Override method for deleting elements.
     * @param id - id of element which should be replaced.
     * @return - boolean value:
     *              true - if element was successfully deleted;
     *              false - otherwise.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        Integer position = this.findPositionById(id);
        if (position != null) {
            this.elements.remove(position);
            result = true;
        }
        return result;
    }

    /**
     * Override method for looking for an elements by their`s id.
     * @param id - element id.
     * @return - the element which was found
     * or null if there are no elements with this id was found.
     */
    @Override
    public T findById(String id) {
        T result = null;
        Iterator<T> it = this.elements.iterator();
        while (it.hasNext()) {
            T value = it.next();
            if (value != null && value.getId().equals(id)) {
                result = value;
                break;
            }
        }
        return result;
    }

    /**
     * Private method for looking for a position of element in the store by the element id.
     * @param id - element id.
     * @return - position of element
     *              or null if there is no element was found.
     */
    private Integer findPositionById(String id) {
        Integer result = null;
        int position = -1;
        Iterator<T> it = this.elements.iterator();
        while (it.hasNext()) {
            T value = it.next();
            position++;
            if (value != null && value.getId().equals(id)) {
                result = position;
                break;
            }
        }
        return result;
    }
}
