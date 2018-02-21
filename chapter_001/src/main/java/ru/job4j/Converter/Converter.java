package ru.job4j.calculator;

/**
 * Корвертор валюты.
 */
/**
* currency converter.
*
* @author Alexander Kordyukov (alex-programm@yandex.ru)
* @version $Id$
* @since 0.1
*/ 
public class Converter {
    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return (value/70);
    }

    /**
     * Конвертируем евро в рубли.
     * @param value Евро.
     * @return рубли.
     */
    public int euroToRuble(int value) {
        return (value*70);
    }
	
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     */
    public int rubleToDollar(int value) {
        return (value/60);
    }
	
    /**
     * Конвертируем доллары в рубли.
     * @param value Доллары.
     * @return рубли.
     */
    public int dollarToRuble(int value) {
        return (value*60);
    }	
}