package ru.job4j.calculator;

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
public class ConverterTest {
    /**
	* Test rubleToDollar.
	*/ @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1D));
    }
	
    /**
	* Test dollarToRuble.
	*/ @Test
    public void whenDollarTo60RubleThen1() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(1);
        assertThat(result, is(60D));
    }	

    /**
	* Test rubleToEuro.
	*/ @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1D));
    }
	
	/**
	* Test euroToRuble.
	*/ @Test
    public void whenEuroTo70RubleThen1() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(1);//.rubleToEuro(70);
        assertThat(result, is(70D));
    }
}