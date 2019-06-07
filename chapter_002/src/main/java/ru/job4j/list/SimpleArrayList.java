package ru.job4j.list;

/**
 * SimpleArrayList<E> - класс, реализующий односвязный список.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayList<E> {
    /** Текущее количество элементов в списке. */
    private int size;
    /** Ссылка на 1-ый элемент (узел). */
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаляет первый элемент в списке.
     */
    public E delete() {
        E result = this.first.data;
        Node<E> temp = this.first.next;
        this.first.data = null;
        this.first.next = null;
        this.first = temp;
        this.size--;
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        /** Данные. */
        E data;

        /** Ссылка на следующий узел. */
        Node<E> next;

        /**
         * Конструктор.
         * @param data - данные.
         */
        Node(E data) {
            this.data = data;
        }
    }
}
