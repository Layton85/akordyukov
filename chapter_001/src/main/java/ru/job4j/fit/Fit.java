package ru.job4j.calculator;

/**
 * ��������� ������� ���������� ����
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Fit {

	/**
	* ��������� ��� ��� �������
	* @param height ����
	* @return weight ���
	*/
    double manWeight(double height) {
        double weight = (height - 100)*1.15;
		return weight;
    }

	/**
	* ��������� ��� ��� �������
	* @param height ����
	* @return weight ���
	*/
    double womanWeight(double height) {
        double weight = (height - 100)*1.15;
		return weight;
    }
}