package ru.job4j.fit;
/**
 * Программа расчета идеального веса
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Fit {
	/**  Коэффициент роста для мужчин. */
	private static final double MAN_HEIGHT = 100;

	/**  Коэффициент роста для женщин. */
	private static final double WOMAN_HEIGHT = 110;

	/**  Коэффициент для веса. */
	private static final double K_WEIGHT = 1.15;

	/**
	* Идеальный вес для мужчины
	* @param height рост
	* @return weight вес
	*/
    double manWeight(double height) {
		final double weight = (height - MAN_HEIGHT) * K_WEIGHT;
		return weight;
    }

	/**
	* Идеальный вес для женщины
	* @param height рост
	* @return weight вес
	*/
    double womanWeight(double height) {
		final double weight = (height - WOMAN_HEIGHT) * K_WEIGHT;
		return weight;
    }
}