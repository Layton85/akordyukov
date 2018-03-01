package ru.job4j.condition;

/**
 * Расчет расстояния между двумя точками в двумерной декартовой системе координат
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Point {
    /**  координата x текущей точки. */
    private int x;

    /**  координата y текущей точки. */
    private int y;

    /**
     * конструктор класса Point
     * @param x координата x текущей точки.
     * @param y координата y текущей точки.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод для расчета расстояния между двумя точками в двумерной декартовой системе координат
     * @param that - переданный объект второй точки (типа Point)
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }

    /**
     * main - вывод на консоль данных расчета расстояния между двумя точками в двумерной декартовой системе координат
     * @param arg - arg
     */
    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);

        // сделаем вызов метода
        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);

        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}
