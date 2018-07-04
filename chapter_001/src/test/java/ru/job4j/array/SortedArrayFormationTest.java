package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SortedArrayFormationTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortedArrayFormationTest {
    @Test
    public void whenFormateArrayFrom3ElementsArrayAnd4ElementsArrayWithoutIntersection() {
        int[] a = new int[] {-5, -3, -1};
        int[] b = new int[] {0, 2, 4, 6};
        SortedArrayFormation sortedArray = new SortedArrayFormation(a, b);
        int[] expect = new int[] {-5, -3, -1, 0, 2, 4, 6};
        int[] result = sortedArray.getAr();
        assertThat(result, is(expect));
    }

    @Test
    public void whenFormateArrayFrom4ElementsArrayAnd3ElementsArrayWithoutIntersection() {
        int[] a = new int[]{0, 2, 4, 6};
        int[] b = new int[]{-5, -3, -1};
        SortedArrayFormation sortedArray = new SortedArrayFormation(a, b);
        int[] expect = new int[]{-5, -3, -1, 0, 2, 4, 6};
        int[] result = sortedArray.getAr();
        assertThat(result, is(expect));
    }

    @Test
    public void whenFormateArrayFrom3ElementsArrayAnd4ElementsArrayWithIntersection() {
        int[] a = new int[] {1, 2, 5};
        int[] b = new int[] {0, 2, 4, 6};
        SortedArrayFormation sortedArray = new SortedArrayFormation(a, b);
        int[] expect = new int[] {0, 1, 2, 2, 4, 5, 6};
        int[] result = sortedArray.getAr();
        assertThat(result, is(expect));
    }

    @Test
    public void whenFormateArrayFrom4ElementsArrayAnd3ElementsArrayWithIntersection() {
        int[] a = new int[] {0, 2, 4, 6};
        int[] b = new int[] {1, 2, 5};
        SortedArrayFormation sortedArray = new SortedArrayFormation(a, b);
        int[] expect = new int[] {0, 1, 2, 2, 4, 5, 6};
        int[] result = sortedArray.getAr();
        assertThat(result, is(expect));
    }

    @Test
    public void whenFormateArrayFrom3ElementsArrayAnd3ElementsArrayWithIntersection() {
        int[] a = new int[] {0, 2, 4};
        int[] b = new int[] {1, 3, 5};
        SortedArrayFormation sortedArray = new SortedArrayFormation(a, b);
        int[] expect = new int[] {0, 1, 2, 3, 4, 5};
        int[] result = sortedArray.getAr();
        assertThat(result, is(expect));
    }
}
