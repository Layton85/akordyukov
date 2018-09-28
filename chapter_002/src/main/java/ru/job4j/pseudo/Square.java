package ru.job4j.pseudo;

/**
 * Square - the class representing a geometrical figure square.
 * Square implements interface Shape.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Square implements Shape {
    /**
     * The method implements method draw() from interface Shape for figure square.
     * @return String with drawing.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("*****");
        pic.append(System.lineSeparator());
        pic.append("*   *");
        pic.append(System.lineSeparator());
        pic.append("*   *");
        pic.append(System.lineSeparator());
        pic.append("*****");
        return pic.toString();
    }
}
