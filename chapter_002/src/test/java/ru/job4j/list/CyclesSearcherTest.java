package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * CyclesSearcherTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CyclesSearcherTest {
    private CyclesSearcher searcher = new CyclesSearcher();
    private CyclesSearcher.Node<Integer> first = null;
    private CyclesSearcher.Node<Integer> second = null;
    private CyclesSearcher.Node<Integer> third = null;
    private CyclesSearcher.Node<Integer> fourth = null;

    private void setUpDifferentData() {
        first = new CyclesSearcher.Node<>(1);
        second = new CyclesSearcher.Node<>(2);
        third = new CyclesSearcher.Node<>(3);
        fourth = new CyclesSearcher.Node<>(4);
    }

    private void setUpIdenticalData() {
        first = new CyclesSearcher.Node<>(3);
        second = new CyclesSearcher.Node<>(3);
        third = new CyclesSearcher.Node<>(3);
        fourth = new CyclesSearcher.Node<>(3);
    }

    @Test
    public void whenListWithoutCycle() {
        setUpDifferentData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(false));
    }

    @Test
    public void whenClosedCycleAsALoop41() {
        setUpDifferentData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenClosedCycleAsALoop42() {
        setUpDifferentData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = second;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenClosedCycleAsALoop43() {
        setUpDifferentData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = third;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenClosedCycleAsALoop44() {
        setUpDifferentData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fourth;
        assertThat(searcher.hasCycle(first), is(true));
    }


    @Test
    public void whenClosedCycleAsALoop31() {
        setUpDifferentData();
        first.next = second;
        second.next = third;
        third.next = first;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }


    @Test
    public void whenClosedCycleAsALoop32() {
        setUpDifferentData();
        first.next = second;
        second.next = third;
        third.next = second;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenClosedCycleAsALoop33() {
        setUpDifferentData();
        first.next = second;
        second.next = third;
        third.next = third;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenClosedCycleAsALoop21() {
        setUpDifferentData();
        first.next = second;
        second.next = first;
        third.next = fourth;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenClosedCycleAsALoop22() {
        setUpDifferentData();
        first.next = second;
        second.next = second;
        third.next = fourth;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenClosedCycleAsALoop11() {
        setUpDifferentData();
        first.next = first;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenListWithIdenticalValuesWithoutCycle() {
        setUpIdenticalData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(false));
    }

    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop41() {
        setUpIdenticalData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop42() {
        setUpIdenticalData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = second;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop43() {
        setUpIdenticalData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = third;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop44() {
        setUpIdenticalData();
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fourth;
        assertThat(searcher.hasCycle(first), is(true));
    }


    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop31() {
        setUpIdenticalData();
        first.next = second;
        second.next = third;
        third.next = first;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }


    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop32() {
        setUpIdenticalData();
        first.next = second;
        second.next = third;
        third.next = second;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop33() {
        setUpIdenticalData();
        first.next = second;
        second.next = third;
        third.next = third;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop21() {
        setUpIdenticalData();
        first.next = second;
        second.next = first;
        third.next = fourth;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop22() {
        setUpIdenticalData();
        first.next = second;
        second.next = second;
        third.next = fourth;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }

    @Test
    public void whenListWithIdenticalValuesWithClosedCycleAsALoop11() {
        setUpIdenticalData();
        first.next = first;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        assertThat(searcher.hasCycle(first), is(true));
    }
}