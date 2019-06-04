package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

/**
 * SimpleArrayTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {
    private SimpleArray<Integer> wrapper;
    Iterator<Integer> it;

    @Before
    public void setUp() {
        wrapper = new SimpleArray<>(3);
    }

    public void setUpIterator() {
        it = wrapper.iterator();
    }

    public void fillInArray() {
        for (int i = 5; i <= 7; i++) {
            wrapper.add(i);
        }
    }

    @Test
    public void whenAddThanValueIsRequired() {
        wrapper.add(5);
        assertThat(wrapper.get(0), is(5));
    }

    @Test
    public void whenAddTooManyElemmentsThanReturnFalse() {
        wrapper.add(5);
        wrapper.add(6);
        wrapper.add(7);
        assertThat(wrapper.add(8), is(false));
    }

    @Test
    public void whenSetThanNewValueIsRequired() {
        wrapper.add(10);
        wrapper.set(0, 5);
        assertThat(wrapper.get(0), is(5));
    }

    @Test
    public void whenSetThanOldValueIsRequired() {
        wrapper.add(5);
        Integer oldValue = wrapper.set(0, 25);
        assertThat(oldValue, is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetThanIndexOutOfBoundsExceptionRequired() {
        wrapper.add(5);
        wrapper.set(1, 25);
    }

    @Test
    public void whenRemoveThanReturnOldValueAndNewValueIsNull() {
        wrapper.add(10);
        assertThat(wrapper.remove(0), is(10));
        assertNull(wrapper.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveThanIndexOutOfBoundsExceptionRequired() {
        wrapper.add(5);
        wrapper.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetThanIndexOutOfBoundsExceptionRequired() {
        wrapper.add(5);
        wrapper.get(1);
    }

    @Test
    public void repeatedHasNextInvocationDoesntAffectRetrievalOrder() {
        this.setUpIterator();
        this.fillInArray();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnElementsSequentally() {
        this.setUpIterator();
        this.fillInArray();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void hasNextAndNextOrientedOnPositionButNotOnSize() {
        this.setUpIterator();
        wrapper.add(5);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}