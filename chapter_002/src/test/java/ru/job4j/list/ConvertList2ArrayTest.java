package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConvertList2ArrayTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2ArrayTest {
    @Test
    public void when2ElementsAndRows3Then3x1() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2),
                3
        );
        int[][] expect = {
                {1},
                {2},
                {0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when7ElementsAndRows3Then3x3() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when12ElementsAndRows3Then3x4() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
                3
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when7ElementsAndRows0Then0x0() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                0
        );
        int[][] expect = new int[0][0];
        assertThat(result, is(expect));
    }

    @Test
    public void when0ElementsAndRows3Then0x0() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(),
                3
        );
        int[][] expect = new int[0][0];
        assertThat(result, is(expect));
    }

    @Test
    public void when0ElementsAndRows0Then0x0() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(),
                0
        );
        int[][] expect = new int[0][0];
        assertThat(result, is(expect));
    }
}
