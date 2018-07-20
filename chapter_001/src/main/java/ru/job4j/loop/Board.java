package ru.job4j.loop;

/**
 * Class chessboard.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /**
     * The method draws a chessboard in a pseudo-graphic
     * @param width number of cells in a chessboard in width
     * @param height number of cells in a chessboard in height
     * @return chessboard as a string
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}