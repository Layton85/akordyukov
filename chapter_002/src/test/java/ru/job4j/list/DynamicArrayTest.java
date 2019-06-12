package ru.job4j.list;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Iterator;
import java.lang.IndexOutOfBoundsException;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * DynamicArrayTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DynamicArrayTest {
    private DynamicArray<Integer> list;
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        list = new DynamicArray<>(3);
    }

    public void setUpIterator() {
        it = list.iterator();
    }

    public void fillInArray() {
        for (int i = 1; i <= 3; i++) {
            list.add(i);
        }
    }

    @Test
    public void whenAddThenReturnTrue() {
        assertThat(list.add(1), is(true));
        assertThat(list.add(2), is(true));
        assertThat(list.add(3), is(true));
        assertThat(list.add(4), is(true));
        assertThat(list.add(5), is(true));
        assertThat(list.add(6), is(true));
        assertThat(list.add(7), is(true));
        assertThat(list.add(8), is(true));
        assertThat(list.add(9), is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetWithoutGrowUp() {
        list.add(1);
        list.add(2);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        list.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetWithGrowUp() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(4));
        list.get(4);
    }

    @Test
    public void repeatedHasNextInvocationDoesNotAffectRetrievalOrder() {
        this.setUpIterator();
        this.fillInArray();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void nextShouldReturnElementsConsistently() {
        this.setUpIterator();
        this.fillInArray();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void hasNextAndNextAreOrientedOnPositionButNotOnSize() {
        this.setUpIterator();
        list.add(1);
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenHasNextButContainerGrewUpThenConcurrentModificationException() {
        this.setUpIterator();
        this.fillInArray();
        list.add(4);
        it.hasNext();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenNextButContainerGrewUpThenConcurrentModificationException() {
        this.setUpIterator();
        this.fillInArray();
        list.add(4);
        it.next();
    }
}