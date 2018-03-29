package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MaxTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {
    // tests for max(int first, int second)
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstGreaterSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstEqualSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 2);
        assertThat(result, is(2));
    }

    // tests for max(int first, int second, int third)
    //first < second
    @Test
    public void whenFirstLessSecondAndThirdLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 3, 1);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstLessSecondAndThirdEqualFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 3, 2);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstLessSecondAndThirdGreaterFirstAndLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 4, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenFirstLessSecondAndThirdEqualSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 3, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstLessSecondAndThirdGreaterSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 3, 4);
        assertThat(result, is(4));
    }

    //first > second
    @Test
    public void whenFirstGreaterSecondAndThirdLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 2, 1);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstGreaterSecondAndThirdEqualSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 2, 2);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstGreaterSecondAndThirdGreaterSecondAndLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(4, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenFirstGreaterSecondAndThirdEqualFirst() {
        Max maxim = new Max();
        int result = maxim.max(3, 2, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstGreaterSecondAndThirdGreaterFirst() {
        Max maxim = new Max();
        int result = maxim.max(3, 2, 4);
        assertThat(result, is(4));
    }

    // first == second
    @Test
    public void whenFirstEqualSecondAndThirdLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstEqualSecondAndThirdEqualFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 2, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstEqualSecondAndThirdGreaterFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 2, 3);
        assertThat(result, is(3));
    }
}
