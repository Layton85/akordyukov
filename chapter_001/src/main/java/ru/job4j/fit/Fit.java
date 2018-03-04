package ru.job4j.calculator;
/**
 * Программа расчета идеального веса
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Fit {
	/**  Коэффициент для роста. */
	private static final double K_HEIGHT = 100;

	/**  Коэффициент для веса. */
	private static final double K_WEIGHT = 15;

	/**
	* Идеальный вес для мужчины
	* @param height рост
	* @return weight вес
	*/
    double manWeight(double height) {
		final double weight = (height - K_HEIGHT)*K_WEIGHT;
		return weight;
    }

	/**
	* Идеальный вес для женщины
	* @param height рост
	* @return weight вес
	*/
    double womanWeight(double height) {
		final double weight = (height - K_HEIGHT)*K_WEIGHT;
		return weight;
    }
}