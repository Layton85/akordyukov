package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SimpleQueueTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueueTest {
    private SimpleQueue<Integer> queue = new SimpleQueue<>();

    @Before
    public void setUpTheQueue() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void whenPoll() {
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        queue.poll();
    }
}