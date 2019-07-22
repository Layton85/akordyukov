package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleHashMap - the class modeling behavior of the simple Hash Map.
 * K - key parameter,
 * V - value parameter.
 * SimpleHashMap can`t hold collision elements: this elements do not add.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    /**
     * Static class that models the SimpleHashMap entry.
     * @param <K> - key,
     * @param <V> - value.
     */
    private static class Node<K, V> {
        final K key;
        V value;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
    }

    /** Hash Array. */
    private Node<K, V>[] table;

    /** The number of non-null entries in the SimpleHashMap. */
    private int size;

    /** The default initial capacity - MUST be a power of two. */
    static final int DEFAULT_INITIAL_CAPACITY = 4;

    /** The load factor used when none specified in constructor. */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /** Structural modifications counter. */
    private int modCount = 0;

    /** Constructs the empty SimpleHashMap with DEFAULT_INITIAL_CAPACITY */
    public SimpleHashMap() {
        this.table = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Put method.
     * @param key - the key of the putting entry.
     * @param value - the value of the putting entry.
     * @return - result of the putting element:
     *              collision elements do not put (return false).
     */
    public boolean put(K key, V value) {
        boolean result = false;
        int i = this.indexFor(key.hashCode(), table.length);
        if ((this.table[i] == null) || (this.table[i].getKey().equals(key))) {
            this.table[i] = new Node<>(key, value);
            this.modCount++;
            this.size++;
            if (this.size >= DEFAULT_LOAD_FACTOR * this.table.length) {
                this.table = this.resize();
            }
            result = true;
        }
        return result;
    }

    /**
     * Get method.
     * @param key - key.
     * @return - the value of element with the transferred key.
     */
    public V get(K key) {
        V result = null;
        int i = this.indexFor(key.hashCode(), table.length);
        if ((this.table[i] != null) && (this.table[i].getKey().equals(key))) {
            result = table[i].getValue();
        }
        return result;
    }

    /**
     * Delete method.
     * @param key - key.
     * @return - true if the entry with key = key was deleted,
     *           false if the the entry with this key does not exist, and so it was not deleted.
     */
    public boolean delete(K key) {
        boolean result = false;
        int i = this.indexFor(key.hashCode(), table.length);
        if ((this.table[i] != null) && (this.table[i].getKey().equals(key))) {
            this.table[i] = null;
            this.modCount++;
            this.size--;
            result = true;
        }
        return result;
    }

    /**
     * Help method resize.
     * This method resize and rewrite hash array when the number of it`s elements is too high.
     * @return renovated hash array.
     */
    private Node<K, V>[] resize() {
        int newCap = this.table.length << 1;
        Node<K, V>[] newTable = new Node[newCap];
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                newTable[(newCap - 1) & this.table[i].getKey().hashCode()] = table[i];
                table[i] = null;
            }
        }
        return newTable;
    }

    /** Returns index for hash code h. */
    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    /**
     * Returns an iterator over elements of type {@code K}.
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor;
            private final int expectedModeCount = modCount;
            @Override
            public boolean hasNext() {
                if (this.expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                for (int i = cursor; i < table.length; i++) {
                    if (table[i] != null) {
                        cursor = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].getKey();
            }
        };
    }
}