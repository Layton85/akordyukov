package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConvertMatrix2ListTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

    @Test
    public void when2on1ArrayThenList2() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1},
                {2}
        };
        List<Integer> expect = Arrays.asList(
                1, 2
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

    @Test
    public void when0on0ArrayThenList0() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {};
        List<Integer> expect = new ArrayList<Integer>();
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }
}
