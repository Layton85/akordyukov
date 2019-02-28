package ru.job4j.function;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;

/**
 * FunctionTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FunctionTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Function function = new Function();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        Function function = new Function();
        List<Double> result = function.diapason(5, 8, x -> Math.pow(x, 2) + 1);
        List<Double> expected = Arrays.asList(26D, 37D, 50D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        Function function = new Function();
        final
        List<Double> result = function.diapason(100, 102, x -> Math.log10(x) + 1);
        List<Double> expected = Arrays.asList(3D, Math.log10(101) + 1);
        assertThat(result, is(expected));
    }
}
