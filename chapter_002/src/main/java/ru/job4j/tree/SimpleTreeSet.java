package ru.job4j.tree;

import java.util.*;

/**
 * SimpleTreeSet - class describes TreeSet.
 * E - parameter which extends
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleTreeSet<E extends Comparable<E>> implements SimpleTree<E> {
    /** root element */
    private Node<E> root;

    /** Structural modifications counter. */
    private int modCount = 0;

    /**
     * Constructor.
     * @param root - root node value (null values are not allows).
     */
    public SimpleTreeSet(E root) {
        if (root == null) {
            throw new NullPointerException();
        }
        this.root = new Node<>(root);
    }

    /**
     * The method adds child element into parent.
     * Parent maybe contains the list of children.
     * Null arguments are not allows.
     * @param parent - parent.
     * @param child  - child.
     * @return - the result of addition:
     * true - in success case, otherwise - false.
     */
    @Override
    public boolean add(E parent, E child) {
        if (child == null || parent == null) {
            throw new NullPointerException();
        }
        boolean result = false;
        if (!parent.equals(child)) {
            Optional<Node<E>> parentNode = this.findBy(parent);
            if (parentNode.isPresent() && !this.findBy(child).isPresent()) {
                parentNode.get().add(new Node<E>(child));
                this.modCount++;
                result = true;
            }
        }
        return result;
    }

    /**
     * The method finds the node in SimpleTreeSet with transferred value.
     * @param value - value for search (null values are not allows).
     * @return - an Optional object with node which contains found value.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        if (value == null) {
            throw new NullPointerException();
        }
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                result = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * The method defines if the SimpleTreeSet is binary or not.
     * @return - true if binary,
     *           false - otherwise.
     */
    public boolean isBinary() {
        boolean result = true;
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            Optional<Node<E>> node = this.findBy(it.next());
            if (node.isPresent() && node.get().leaves().size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * Iterator is using pre-order tree traversal (root, left, right).
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E>  cursor = root;
            private final int expectedModeCount = modCount;
            private Deque<Node<E>> buff = new LinkedList<>();
            @Override
            public boolean hasNext() {
                if (this.expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (cursor != null) {
                    if (cursor == root && buff.isEmpty()) {
                        buff.offer(root);
                    }
                    if (!buff.isEmpty()) {
                        result = true;
                    }
                }
                return result;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = cursor.getValue();
                List<Node<E>> list = buff.poll().leaves();
                ListIterator<Node<E>> litr = list.listIterator(list.size());
                while (litr.hasPrevious()) {
                    buff.offerFirst(litr.previous());
                }
                if (buff.isEmpty()) {
                    cursor = null;
                } else {
                    cursor = buff.element();
                }
                return result;
            }
        };
    }

    /**
     * The method returns alternative "layered" iterator.
     * This iterator is using "layered" way of tree traversal (root, the layer of root leaves....)
     * @return - "layered" iterator.
     */
    public Iterator<E> layeredIterator() {
        return new Iterator<E>() {
            private final int expectedModeCount = modCount;
            private Deque<Node<E>> buff = new LinkedList<>(Collections.singletonList(root));
            @Override
            public boolean hasNext() {
                if (this.expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !buff.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> poll = buff.poll();
                buff.addAll(poll.leaves());
                return poll.getValue();
            }
        };
    }
}