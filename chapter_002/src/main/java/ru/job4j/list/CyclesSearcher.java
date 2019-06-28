package ru.job4j.list;

/**
 * CyclesSearcher - class is used for search cycles in the single linked lists.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CyclesSearcher {

     /** Internal class for the list`s nodes.
     * @param <E> - data type.
     */
    static class Node<E> {
        /** Data */
        E data;

        /** links to the next node. */
        Node<E> next;

        /**
         * Constructor.
         * @param data - data.
         */
        Node(E data) {
            this.data = data;
        }
    }

    /**
     * The method searches cycles in the single linked lists.
     * @param first - first element of the list.
     * @return - result of search:
     *              false - no any cycles was found,
     *              true - the list have at least one cycle.
     */
    public boolean hasCycle(Node first) {
        boolean result = false;
        if (first != null) {
            Node slow = first;
            Node fast = first.next;
            while (fast != null) {
                if (fast == slow) {
                    result = true;
                    break;
                }
                slow = slow.next;
                fast = fast.next;
                if (fast != null) {
                    fast = fast.next;
                }
            }
        }
        return result;
    }

    /**
     * This is an alternative for the method hasCycle(Node first).
     * The method searches cycles in the single linked lists.
     * @param first - first element of the list.
     * @return - result of search:
     *              false - no any cycles was found,
     *              true - the list have at least one cycle.
     */
    public boolean hasCycle1(Node first) {
        boolean result = false;
        Node a = first;
        Node b = a;
        while (b.next != null && !result) {
            while (a != b) {
                if (b.next == a) {
                    result = true;
                    break;
                }
                a = a.next;
            }
            if (b.next == b) {
                result = true;
                break;
            }
            a = first;
            b = b.next;
        }
        return result;
    }
}