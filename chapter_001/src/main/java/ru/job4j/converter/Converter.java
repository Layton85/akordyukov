package ru.job4j.converter;

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

    /**  Курс евро. */
    private final int euroRATE = 70;

    /**  Курс доллара. */
    private final int dollarRATE = 60;

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return (value / euroRATE);
    }

    /**
     * Конвертируем евро в рубли.
     * @param value Евро.
     * @return рубли.
     */
    public int euroToRuble(int value) {
        return (value * euroRATE);
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     */
    public int rubleToDollar(int value) {
        return (value / dollarRATE);
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value Доллары.
     * @return рубли.
     */
    public int dollarToRuble(int value) {
        return (value * dollarRATE);
    }
}