package ru.job4j.generic;

/**
 * Store - interface setting behavior of store of some elements.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public interface Store<T extends Base> {
    /**
     * Add method.
     * @param model - element to add.
     */
    void add(T model);

    /**
     * Replace method.
     * @param id - id of element which should be replaced.
     * @param model - new element.
     * @return - boolean value:
     *              true - if element was successfully replaced;
     *              false - otherwise.
     */
    boolean replace(String id, T model);

    /**
     * Delete method.
     * @param id - id of element which should be replaced.
     * @return - boolean value:
     *              true - if element was successfully deleted;
     *              false - otherwise.
     */
    boolean delete(String id);

    /**
     * The method finds element in the store by it`s id.
     * @param id - element id.
     * @return - the element which was found
     * or null if there are no elements with this id was found.
     */
    T findById(String id);
}
