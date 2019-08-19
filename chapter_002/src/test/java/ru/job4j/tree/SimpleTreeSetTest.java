package ru.job4j.tree;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

/**
 * SimpleTreeSetTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleTreeSetTest {
    Iterator<Integer> it;

    @Test(expected = NullPointerException.class)
    public void whenNullRootThenNPE() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(null);
    }

    @Test(expected = NullPointerException.class)
    public void whenAddNullElementThenNPE() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, null);
    }

    @Test(expected = NullPointerException.class)
    public void whenAddNullParentElementThenNPE() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(null, 2);
    }

    @Test(expected = NullPointerException.class)
    public void whenAddNullChildAndNullParentElementThenNPE() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void whenTryToFindNullElementThenNPE() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.findBy(null);
    }

    @Test
    public void whenAddReapetedElementThenItDoesNotAdd() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        assertThat(tree.add(1, 1), is(false));
        assertThat(tree.findBy(1).isPresent(), is(true));
        it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenToTheRemoteTreeLevelAddReapetedElementThenItDoesNotAdd() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        assertThat(tree.add(2, 1), is(false));
        assertThat(tree.findBy(1).isPresent(), is(true));
        it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void when6ElFindLastThen6() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRepeatedHasNextInvocationThenDoesNotAffectRetrievalOrder() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void expectedIteratorWorkingProcess() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, 6);
        tree.add(1, 3);
        tree.add(1, 2);
        tree.add(6, 5);
        tree.add(6, 4);
        tree.add(2, 7);
        tree.add(2, 8);
        tree.add(7, 10);
        tree.add(7, 9);
        tree.add(8, 11);
        it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(11));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenStructuralModificationOfDataContainer() {
        SimpleTree<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        tree.add(3, 5);
        it.hasNext();
    }

    @Test
    public void whenTreeContainsOnlyRootThenItIsBinary() {
        SimpleTreeSet<Integer> tree = new SimpleTreeSet<>(1);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenTreeIsNotBinary() {
        SimpleTreeSet<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, 6);
        tree.add(1, 3);
        tree.add(1, 2);
        tree.add(6, 5);
        tree.add(6, 4);
        tree.add(2, 7);
        tree.add(2, 8);
        tree.add(7, 10);
        tree.add(7, 9);
        tree.add(8, 11);
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenTreeIsBinary() {
        SimpleTreeSet<Integer> tree = new SimpleTreeSet<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        assertThat(tree.isBinary(), is(true));
    }
}