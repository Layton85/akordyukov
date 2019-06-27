package ru.job4j.list;

/**
 * SimpleStack - parametrized Stack based on the SimpleArrayList
 * (simple parametrized single linked list).
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<T> {
    /** Internal container into which the components of the SimpleStack are stored */
    private SimpleArrayList<T> list = new SimpleArrayList<>();

    /**
     * The method deletes head element and returns it.
     * @return - deleted head element.
     * @throws java.lang.NullPointerException - if the SimpleStack is empty.
     */
    public T poll() {
        return this.list.delete();
    }

    /**
     * The method adds new element to the SimpleStack.
     * @param value - new element.
     */
    public void push(T value) {
        this.list.add(value);
    }

    /**
     * The method shows that the stack is empty or not.
     * @return - boolean result:
     *              false - stack is empty
     *              true - stack is not empty
     */
    public boolean empty() {
        return this.list.getSize() == 0;
    }
}
