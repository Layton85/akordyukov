package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Node - class describes nodes in the SimpleTreeSet.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Node<E extends Comparable<E>> {
    /** The list of node`s children. */
    private final List<Node<E>> children = new ArrayList<>();

    /** Node`s value */
    private final E value;

    /**
     * Node constructor.
     * @param value - node`s value.
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Get-method.
     * @return - node`s value.
     */
    public E getValue() {
        return value;
    }

    /**
     * The method adds child into node.
     * @param child - child node.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * The method returns the list of children of node.
     * @return - the list of children of node.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * The method node`s value with another - transferred value.
     * @param that - value to compare.
     * @return - result of values comparison:
     *              true - if they equals,
     *              false - otherwise.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}