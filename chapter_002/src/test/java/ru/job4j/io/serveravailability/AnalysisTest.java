package ru.job4j.io.serveravailability;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringJoiner;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * AnalysisTest.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class AnalysisTest {
    private String s = File.separator;
    private String sourceBase = "." + s + "src" + s + "test" + s + "java" + s + "ru" + s + "job4j"  + s + "io" + s + "serveravailability" + s;
    private String target = "." + s + "src" + s + "test" + s + "java" + s + "ru" + s + "job4j" + s + "io"  + s + "serveravailability" + s + "unavaliable.csv";

    Analysis analysis = new Analysis();

    private StringJoiner writeIntervalsAndReadTarget(String src) {
        analysis.unavailable(src, target);
        StringJoiner strings = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(strings::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    @Test
    public void whenTwoIntervals() {
        String source = this.sourceBase.concat("logTwoIntervals.csv");
        StringJoiner strings = this.writeIntervalsAndReadTarget(source);
        String expected = new String("10:57:01;10:59:01" + System.lineSeparator() + "11:01:02;11:02:02");
        assertThat(strings.toString().equals(expected), is(true));
    }

    @Test
    public void whenNoIntervals() {
        String source = this.sourceBase.concat("logZeroIntervals.csv");
        StringJoiner strings = this.writeIntervalsAndReadTarget(source);
        String expected = new String();
        assertThat(strings.toString().equals(expected), is(true));
    }

    @Test
    public void whenOpenEndInterval() {
        String source = this.sourceBase.concat("logOpenEnd.csv");
        StringJoiner strings = this.writeIntervalsAndReadTarget(source);
        String expected = new String("10:57:01;11:02:02");
        assertThat(strings.toString().equals(expected), is(true));
    }

    @Test
    public void whenOneInterval() {
        String source = this.sourceBase.concat("logOneInterval.csv");
        StringJoiner strings = this.writeIntervalsAndReadTarget(source);
        String expected = new String("10:56:01;10:59:01");
        assertThat(strings.toString().equals(expected), is(true));
    }

    @Test
    public void whenEmptyLog() {
        String source = this.sourceBase.concat("logEmpty.csv");
        StringJoiner strings = this.writeIntervalsAndReadTarget(source);
        String expected = new String();
        assertThat(strings.toString().equals(expected), is(true));
    }
}