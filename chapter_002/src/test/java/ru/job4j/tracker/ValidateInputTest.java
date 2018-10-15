package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ValidateInputTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {
    /** default system output - in console */
    private final PrintStream stdout = System.out;

    /** buffer in memory for the output */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /** The method reload system output to the variable out. */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /** The method changes output to the standart system output in console. */
    @After
    public void backOutput() {
        System.setOut(new PrintStream(this.stdout));
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"invalid", "1"}));
        input.ask("Enter", new int[] {1});
        assertThat(
                this.out.toString(),
                is(
                        String.format("Please enter validate value.%n")
                )
        );
    }

    @Test
    public void whenInputOutOfMenuRange() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"-1", "1"}));
        input.ask("Enter", new int[] {1});
        assertThat(
                this.out.toString(),
                is(
                        String.format("Please select key from menu.%n")
                )
        );
    }

    @Test
    public void whenInputCorrect() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"1"}));
        input.ask("Enter", new int[] {1});
        assertThat(
                this.out.toString(),
                is(
                        new String("")
                )
        );
    }
}