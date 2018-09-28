package ru.job4j.pseudo;

/**
 * Paint - main class with entry point in programm.
 * Paint provides drawing triangle and square.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * The method draws on a console geometrical figure received as parameter.
     * @param shape - reference on a figure object.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    /*
    * main function - entry point in programm.
    * input of parameters isn't provided.
    */
    public static void main(String[] args) {
        Paint paint = new Paint();
        Shape shape = new Triangle();
        paint.draw(shape);
        shape = new Square();
        paint.draw(shape);
    }
}
