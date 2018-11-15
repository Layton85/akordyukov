package ru.job4j.coffeemachine;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * CoffeeMachineTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachineTest {
    @Test
    public void whenValueIs50AndPriceIs35() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] expected = new int[] {10, 5};
        assertThat(machine.changes(50, 35), is(expected));
    }

    @Test
    public void whenValueIs500AndPriceIs111() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] expected = new int[] {200, 100, 50, 10, 10, 10, 5, 2, 2};
        assertThat(machine.changes(500, 111), is(expected));
    }

    @Test
    public void whenValueIs50AndPriceIs50() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] expected = new int[] {};
        assertThat(machine.changes(50, 50), is(expected));
    }

    @Test
    public void whenValueIs50AndPriceIs51() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] expected = new int[] {};
        assertThat(machine.changes(50, 51), is(expected));
    }
}
