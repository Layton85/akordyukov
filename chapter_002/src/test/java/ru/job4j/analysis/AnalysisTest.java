package ru.job4j.analysis;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

/**
 * AnalysisTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class AnalysisTest {
    List<Analysis.User> previous = new ArrayList<>();
    List<Analysis.User> current = new ArrayList<>();
    Analysis analysis = new Analysis();

    @Test
    public void whenEmptyLists() {
        Analysis.Info expected = new Analysis.Info(0, 0, 0);
        assertThat(analysis.diff(previous, current).equals(expected), is(true));
    }

    @Test
    public void whenPreviousEmpty() {
        current.add(new Analysis.User(4, "Jim"));
        Analysis.Info expected = new Analysis.Info(1, 0, 0);
        assertThat(analysis.diff(previous, current).equals(expected), is(true));
    }

    @Test
    public void whenCurrentEmpty() {
        previous.add(new Analysis.User(4, "Jim"));
        Analysis.Info expected = new Analysis.Info(0, 0, 1);
        assertThat(analysis.diff(previous, current).equals(expected), is(true));
    }

    @Test
    public void whenOnlyChanged() {
        previous.add(new Analysis.User(1, "Alex"));
        previous.add(new Analysis.User(2, "Ugin"));
        previous.add(new Analysis.User(3, "John"));
        current.addAll(previous);
        current.set(1, new Analysis.User(2, "Evgen"));
        Analysis.Info expected = new Analysis.Info(0, 1, 0);
        assertThat(analysis.diff(previous, current).equals(expected), is(true));
    }

    @Test
    public void whenAllPositionsChanged() {
        previous.add(new Analysis.User(1, "Alex"));
        previous.add(new Analysis.User(2, "Ugin"));
        previous.add(new Analysis.User(3, "John"));
        current.addAll(previous);
        current.add(new Analysis.User(4, "Jim"));
        current.remove(0);
        current.set(0, new Analysis.User(2, "Evgen"));
        Analysis.Info expected = new Analysis.Info(1, 1, 1);
        assertThat(analysis.diff(previous, current).equals(expected), is(true));
    }

    @Test
    public void whenCurrentListLessThanPrevious() {
        previous.add(new Analysis.User(1, "Alex"));
        previous.add(new Analysis.User(2, "Ugin"));
        previous.add(new Analysis.User(3, "John"));
        current.addAll(previous);
        current.remove(0);
        current.set(0, new Analysis.User(2, "Evgen"));
        Analysis.Info expected = new Analysis.Info(0, 1, 1);
        assertThat(analysis.diff(previous, current).equals(expected), is(true));
    }

    @Test
    public void whenCurrentListGreaterThanPrevious() {
        previous.add(new Analysis.User(1, "Alex"));
        previous.add(new Analysis.User(2, "Ugin"));
        previous.add(new Analysis.User(3, "John"));
        current.addAll(previous);
        current.add(new Analysis.User(4, "Jim"));
        current.add(new Analysis.User(5, "Lisa"));
        current.remove(0);
        current.set(0, new Analysis.User(2, "Evgen"));
        Analysis.Info expected = new Analysis.Info(2, 1, 1);
        assertThat(analysis.diff(previous, current).equals(expected), is(true));
    }
}