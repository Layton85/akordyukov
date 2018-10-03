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
}
