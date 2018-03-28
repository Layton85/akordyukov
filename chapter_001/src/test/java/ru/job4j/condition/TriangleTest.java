package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * TriangleTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = 2D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    public void whenTriangleDoesNotExistOne() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(1, 1);
        Point c = new Point(2, 2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0));
    }

    @Test
    public void whenTriangleDoesNotExistTwo() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(1, -1);
        Point c = new Point(2, -2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0));
    }

    @Test
    public void whenTriangleDoesNotExistThree() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(-1, -1);
        Point c = new Point(-2, -2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0));
    }

    @Test
    public void whenTriangleDoesNotExistFour() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(-1, 1);
        Point c = new Point(-2, 2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0));
    }

    @Test
    public void whenTriangleDoesNotExistFive() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        Point c = new Point(0, 0);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0));
    }

    @Test
    public void whenTriangleDoesNotExistSix() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        Point c = new Point(2, -2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0));
    }

    @Test
    public void whenTriangleDoesNotExistSeven() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(-2, 2);
        Point c = new Point(-2, 2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0));
    }

    @Test
    public void whenTriangleDoesNotExistEight() {
        // создаем три объекта класса Point.
        Point a = new Point(-2, -2);
        Point b = new Point(0, 0);
        Point c = new Point(-2, -2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0));
    }

}