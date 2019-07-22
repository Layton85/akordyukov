package ru.job4j.map;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SimpleHashMapTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMapTest {
    private SimpleHashMap<Integer, String> map;
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        map = new SimpleHashMap<>();
    }

    @Test
    public void whenPutElementsThenGetIt() {
        map.put(1, "Alpha");
        map.put(2, "Betta");
        map.put(3, "Gamma");
        map.put(4, "Delta");
        map.put(5, "Epsilon");
        assertThat(map.get(1), is("Alpha"));
        assertThat(map.get(2), is("Betta"));
        assertThat(map.get(3), is("Gamma"));
        assertThat(map.get(4), is("Delta"));
        assertThat(map.get(5), is("Epsilon"));
    }

    @Test
    public void whenPutElementsWithIdenticalKeysThenRewriteFirstElement() {
        map.put(1, "Alpha");
        map.put(1, "Centavra");
        assertThat(map.get(1), is("Centavra"));
        assertThat(map.get(1), is("Centavra"));
    }

    @Test
    public void whenGetElementsAndKeysAreNonEquals() {
        map.put(2, "Betta");
        assertNull(map.get(6));
    }

    @Test
    public void whenDeleteThenGetNoAnyElements() {
        map.put(1, "Alpha");
        assertThat(map.get(1), is("Alpha"));
        map.delete(1);
        assertNull(map.get(1));
    }

    @Test
    public void whenDeleteAndThenGetNull() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.put(1, "Alpha");
        map.put(2, "Betta");
        map.put(3, "Gamma");
        assertThat(map.get(3), is("Gamma"));
        map.delete(3);
        assertNull(map.get(3));
        assertThat(map.get(1), is("Alpha"));
        map.delete(1);
        assertNull(map.get(1));
        assertThat(map.get(2), is("Betta"));
        map.delete(2);
        assertNull(map.get(2));
    }

    @Test
    public void whenDeleteElementsAndKeysAreNonEquals() {
        map.put(2, "Betta");
        assertThat(map.delete(6), is(false));
        assertThat(map.get(2), is("Betta"));
    }

    @Test
    public void whenDeleteElementsAndThenPutAnotherOnes() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.put(1, "Alpha");
        map.put(2, "Betta");
        assertThat(map.get(1), is("Alpha"));
        map.delete(1);
        assertNull(map.get(1));
        assertThat(map.get(2), is("Betta"));
        map.delete(2);
        assertNull(map.get(2));
        map.put(2, "Tom");
        assertThat(map.get(2), is("Tom"));
        map.put(1, "Sawyer");
        assertThat(map.get(1), is("Sawyer"));
    }

    @Test
    public void whenHasNextThenCursorDoesNotMove() {
        map.put(1, "Alpha");
        map.put(2, "Betta");
        it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenThereAreNoElementsThenNoSuchElementException() {
        map.put(1, "Alpha");
        map.put(2, "Betta");
        it = map.iterator();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void whenIteratesThenReturnsElementsSequentially() {
        map.put(1, "Alpha");
        map.put(2, "Betta");
        map.put(3, "Gamma");
        map.put(4, "Delta");
        map.put(5, "Epsilon");
        it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutElementAfterIteratorLoadThenConcurrentModificationException() {
        it = map.iterator();
        assertThat(map.put(1, "Alpha"), is(true));
        it.hasNext();
    }

    @Test
    public void whenStructureChangesThenIteratorWorksNormally() {
        map.put(124, "Alpha");
        map.put(1, "Betta");
        map.put(2, "Gamma");
        map.put(3, "Delta");
        it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(124));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenDeleteElementAfterHasNextThenConcurrentModificationException() {
        map.put(1, "Alpha");
        it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(map.delete(1), is(true));
        it.next();
    }
}