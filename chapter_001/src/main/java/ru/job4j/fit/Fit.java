package ru.job4j.calculator;
/**
 * Программа расчета идеального веса
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Fit {

	/**
	* Идеальный вес для мужчины
	* @param height рост
	* @return weight вес
	*/
    double manWeight(double height) {
		final double weight = (height - 100)*1.15;
		return weight;
    }

	/**
	* Идеальный вес для женщины
	* @param height рост
	* @return weight вес
	*/
    double womanWeight(double height) {
		final double weight = (height - 100)*1.15;
		return weight;
    }
}