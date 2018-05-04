package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MatrixTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MatrixTest {
    @Test
    public void when0on0() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(0);
        int[][] expect = {};
        assertThat(table, is(expect));
    }

    @Test
    public void when1on1() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(1);
        int[][] expect = {
                {1}
        };
        assertThat(table, is(expect));
    }

    @Test
    public void when2on2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expect = {
                {1, 2},
                {2, 4}
        };
        assertThat(table, is(expect));
    }

    @Test
    public void when3on3() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(3);
        int[][] expect = {
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9}
        };
        assertThat(table, is(expect));
    }
}
