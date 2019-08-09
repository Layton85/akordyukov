package ru.job4j.tree;

import java.util.Optional;

/**
 * SimpleTree - interface setting behavior of simple tree.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * The method adds child into the parent node.
     * Parent can hold the list of children.
     * @param parent - parent.
     * @param child - child.
     * @return - true if child was added, otherwise false.
     */
    boolean add(E parent, E child);

    /**
     * The method find transferred value in the tree.
     * @param value - value for search.
     * @return - if value was found - the Optional object with Node including this value,
     *              otherwise - empty Optional object.
     */
    Optional<Node<E>> findBy(E value);
}