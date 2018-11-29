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
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded2Order() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("middle", 3));
        queue.put(new Task("urgent", 1));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded3Order() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded4Order() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("middle", 3));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("low", 5));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded5Order() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityWasAdded6Order() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("urgent", 1));
        queue.put(new Task("low", 5));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenQueueWasEmpty() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("first and single", 100));
        Task result = queue.take();
        assertThat(result.getDesc(), is("first and single"));
    }

    @Test
    public void whenTheSamePriorityWasAdded() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("first urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("second urgent", 1));
        Task first = queue.take();
        Task second = queue.take();
        assertThat((first.getDesc().equals("first urgent") && second.getDesc().equals("second urgent")), is(true));
    }
}
