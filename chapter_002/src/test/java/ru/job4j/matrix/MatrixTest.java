package ru.job4j.matrix;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * MatrixTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MatrixTest {
    @Test
    public void whenMatrix3x2ThenListLength6() {
        Integer[][] matrix = {
            {1, 2},
            {3, 4},
            {5, 6}
        };
        List<Integer> expectedList = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(
                new Matrix().matrixToList(matrix).equals(expectedList),
                is(true)
        );
    }
}
