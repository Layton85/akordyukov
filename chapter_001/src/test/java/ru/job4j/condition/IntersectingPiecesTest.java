package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * IntersectingPiecesTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class IntersectingPiecesTest {
    @Test
    public void whenABPieceInTheLeftOFCDPieceWithoutIntersection() {
        int a = -50;
        int b = -20;
        int c = -10;
        int d = 10;
        IntersectingPieces intersect = new IntersectingPieces(a, b, c, d);
        boolean expect = false;
        boolean result = intersect.getIntersectionInformation();
        assertThat(result, is(expect));
    }

    @Test
    public void whenABPieceInTheLeftOFCDPieceWithIntersection() {
        int a = -30;
        int b = 0;
        int c = -10;
        int d = 10;
        IntersectingPieces intersect = new IntersectingPieces(a, b, c, d);
        boolean expect = true;
        boolean result = intersect.getIntersectionInformation();
        assertThat(result, is(expect));
    }

    @Test
    public void whenABPieceInTheRightOFCDPieceWithIntersection() {
        int a = 0;
        int b = 30;
        int c = -10;
        int d = 10;
        IntersectingPieces intersect = new IntersectingPieces(a, b, c, d);
        boolean expect = true;
        boolean result = intersect.getIntersectionInformation();
        assertThat(result, is(expect));
    }

    @Test
    public void whenABPieceInTheRightOFCDPieceWithoutIntersection() {
        int a = 20;
        int b = 50;
        int c = -10;
        int d = 10;
        IntersectingPieces intersect = new IntersectingPieces(a, b, c, d);
        boolean expect = false;
        boolean result = intersect.getIntersectionInformation();
        assertThat(result, is(expect));
    }
}