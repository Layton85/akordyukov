package ru.job4j.fit;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Alexander Kordyukov (alex-programm@yandex.ru)
* @version $Id$
* @since 0.1
*/
public class FitTest {

    @Test
    public void manWeight() {
        Fit fit = new Fit();
        double weight = fit.manWeight(180);
        assertThat(weight, is(92D));
    }

    @Test
    public void womanWeight() {
        Fit fit = new Fit();
        double weight = fit.womanWeight(170);
        assertThat(weight, is(69D));
    }
}