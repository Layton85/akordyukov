package ru.job4j.list;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * DynamicLinkedListTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DynamicLinkedListTest {
    private DynamicLinkedList<Integer> list;
    private Iterator<Integer> it;

    @Before
    public void beforeTest() {
        list = new DynamicLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public void setUpIterator() {
        it = list.iterator();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAndThenGet() {
        assertThat(list.get(1), is(2));
        assertThat(list.get(0), is(3));
        assertThat(list.get(2), is(1));
        list.get(3);
    }

    @Test
    public void repeatedHasNextInvocationDoesNotAffectRetrievalOrder() {
        this.setUpIterator();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void nextShouldReturnElementsConsistently() {
        this.setUpIterator();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenHasNextButContainerGrewUpThenConcurrentModificationException() {
        this.setUpIterator();
        list.add(4);
        it.hasNext();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenNextButContainerGrewUpThenConcurrentModificationException() {
        this.setUpIterator();
        list.add(4);
        it.next();
    }
}
