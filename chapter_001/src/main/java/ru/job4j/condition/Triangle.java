package ru.job4j.condition;

/**
 * Класс треугольника (со способностью вычисления своей площади).
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Triangle {
    /** точка с координатами вершины A треугольника */
    private Point a;

    /** точка с координатами вершины B треугольника */
    private Point b;

    /** точка с координатами вершины C треугольника */
    private Point c;

    /**
     * конструктор класса Triangle
     * @param a точка с координатами вершины A треугольника.
     * @param b точка с координатами вершины И треугольника.
     * @param c точка с координатами вершины С треугольника.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * Формула.
     * (ab + ac + bc) / 2
     *
     * @param ab расстояние между точками A B
     * @param ac расстояние между точками A C
     * @param bc расстояние между точками B C
     * @return Полупериметр.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2D;
    }

    /**
     * Метод, вычисляющий площадь треугольника.
     *
     * @return Возвращает прощадь, если треугольник существует или -1, если треугольника нет.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac)); //применение формулы Герона для расчета площади треугольника
        }
        return rsl;
    }

    /**
     * Метод, проверяющий можно ли построить треугольник с данными длинами сторон.
     *
     * @param ab Длина от точки a b.
     * @param ac Длина от точки a c.
     * @param bc Длина от точки b c.
     * @return true - построение треугольника по заданным точкам возможно, false - невозможно
     */
    private boolean exist(double ab, double ac, double bc) {
        boolean rsl = true;

        if ((ab + ac) <= bc) {
            rsl = false;
        } else if ((ab + bc) <= ac) {
            rsl = false;
        } else if ((ac + bc) <= ab) {
            rsl = false;
        }

        return rsl;
    }
}
