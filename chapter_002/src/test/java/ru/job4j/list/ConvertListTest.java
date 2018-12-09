package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConvertListTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertListTest {
    @Test
    public void when2DifferentArraysThen1List() {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        List<Integer> result = new ConvertList().convert(list);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expected));
    }

    @Test
    public void when2SameArraysThen1List() {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4});
        List<Integer> result = new ConvertList().convert(list);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertThat(result, is(expected));
    }

    @Test
    public void when1ArrayThen1List() {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        List<Integer> result = new ConvertList().convert(list);
        List<Integer> expected = Arrays.asList(1, 2);
        assertThat(result, is(expected));
    }

    @Test
    public void when0ArraysThenEmptyList() {
        List<int[]> list = new ArrayList<>();
        List<Integer> result = new ConvertList().convert(list);
        List<Integer> expected = new ArrayList<>();
        assertThat(result, is(expected));
    }
}
