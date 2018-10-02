package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PaintTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    /** default system output - in console */
    private final PrintStream stdout = System.out;

    /** buffer in memory for the output */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * The method reload system output to the variable out.
     * loadOutput() always executes before tests.
     */
    @Before
    public void loadOutput() {
        System.out.println("Execute before method");
        System.setOut(new PrintStream(this.out));
    }

    /**
     *The method changes output to the standart system output in console.
     * backOutput() always executes after tests.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /** Test method for drawing square*/
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("*****")
                                .add("*   *")
                                .add("*   *")
                                .add("*****")
                                .toString()
                )
        );
    }

    /** Test method for drawing triangle*/
    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("  *  ")
                                .append(System.lineSeparator())
                                .append(" * * ")
                                .append(System.lineSeparator())
                                .append("*****")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
