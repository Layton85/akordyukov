package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PriorityQueueTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        assertThat(queue.take().getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded2Order() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("middle", 3));
        queue.put(new Task("urgent", 1));
        assertThat(queue.take().getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded3Order() {
        var queue = new PriorityQueue();
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        assertThat(queue.take().getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded4Order() {
        var queue = new PriorityQueue();
        queue.put(new Task("middle", 3));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("low", 5));
        assertThat(queue.take().getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded5Order() {
        var queue = new PriorityQueue();
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        assertThat(queue.take().getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded6Order() {
        var queue = new PriorityQueue();
        queue.put(new Task("urgent", 1));
        queue.put(new Task("low", 5));
        queue.put(new Task("middle", 3));
        assertThat(queue.take().getDesc(), is("urgent"));
    }

    @Test
    public void whenQueueWasEmpty() {
        var queue = new PriorityQueue();
        queue.put(new Task("first and single", 100));
        assertThat(queue.take().getDesc(), is("first and single"));
    }

    @Test
    public void whenTheSamePriorityWasAdded() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("first urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("second urgent", 1));
        var first = queue.take();
        var second = queue.take();
        assertThat((first.getDesc().equals("first urgent") && second.getDesc().equals("second urgent")), is(true));
    }
}
