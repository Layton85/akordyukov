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
        TreeSet<String> inputTree = new TreeSet<>(List.of(input));
        return new DepartmentSort(inputTree);
    }

    @Test
    public void ascendingOrderSort() {
        List<String> expected = List.of(
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );
        assertThat(prepareData().ascendingSort(), is(expected));
    }

    @Test
    public void descendingOrderSort() {
        List<String> expected = List.of(
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        );
        assertThat(prepareData().descendingSort(), is(expected));
    }
}
