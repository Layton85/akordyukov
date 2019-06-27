package ru.job4j.list;

/**
 * SimpleQueue - the queue (FIFO) based on the two stacks.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<T> {
    /** input stack */
    SimpleStack<T> input = new SimpleStack<>();

    /** output stack */
    SimpleStack<T> output = new SimpleStack<>();

    /**
     * The method deletes and allows to get the element from the head of the queue.
     * @return - deleted element.
     */
    public T poll() {
        if (this.output.empty()) {
            while (!this.input.empty()) {
                this.output.push(this.input.poll());
            }
        }
        return output.poll();
    }

    /**
     * The method adds the element into the queue.
     * @param value - the element to addition.
     */
    public void push(T value) {
        this.input.push(value);
    }
}
