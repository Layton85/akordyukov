package ru.job4j.departmentsort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * DepartmentSortTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DepartmentSortTest {
    /**
     * this method prepare common data (input array, DepartmentSort object) for the other test methods.
     * @return - DepartmentSort object with complete input department list.
     */
    private DepartmentSort prepareData() {
        String[] input = new String[]{
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        };
        TreeSet<String> inputTree = new TreeSet<>(Arrays.asList(input));
        return new DepartmentSort(inputTree);
    }

    @Test
    public void ascendingOrderSort() {
        List<String> expected = new ArrayList<>();
        expected.add("K1");
        expected.add("K1\\SK1");
        expected.add("K1\\SK1\\SSK1");
        expected.add("K1\\SK1\\SSK2");
        expected.add("K1\\SK2");
        expected.add("K2");
        expected.add("K2\\SK1");
        expected.add("K2\\SK1\\SSK1");
        expected.add("K2\\SK1\\SSK2");
        assertThat(prepareData().ascendingSort(), is(expected));
    }

    @Test
    public void descendingOrderSort() {
        List<String> expected = new ArrayList<>();
        expected.add("K2");
        expected.add("K2\\SK1");
        expected.add("K2\\SK1\\SSK2");
        expected.add("K2\\SK1\\SSK1");
        expected.add("K1");
        expected.add("K1\\SK2");
        expected.add("K1\\SK1");
        expected.add("K1\\SK1\\SSK2");
        expected.add("K1\\SK1\\SSK1");
        assertThat(prepareData().descendingSort(), is(expected));
    }
}
