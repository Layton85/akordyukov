package ru.job4j.condition;

/**
 * IntersectingPieces - class including 2 integer numerical pieces.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class IntersectingPieces {
    /** left border of first numerical piece */
    private int a;

    /** right border of first numerical piece */
    private int b;

    /** left border of second numerical piece */
    private int c;

    /** right border of second numerical piece */
    private int d;

    /**
     * Constructor.
     * @param a - left border of first numerical piece.
     * @param b - right border of first numerical piece.
     * @param c - left border of second numerical piece.
     * @param d - right border of second numerical piece.
     */
    public IntersectingPieces(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Get-method for information about intersection.
     * @return result - information about intersection: true - pieces intersect, false - pieces don`t intersect.
     */
    public boolean getIntersectionInformation() {
        boolean result = (this.a <= this.d) && (this.b >= this.c);
        return result;
    }
}