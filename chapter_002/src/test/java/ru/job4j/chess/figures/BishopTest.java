package ru.job4j.chess.figures;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * BishopTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopTest {
    @Test(expected = ImpossibleMoveException.class)
    public void whenD5D5ThenNoWay() {
        new Bishop(Cell.D5).way(Cell.D5, Cell.D5);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenD5D4ThenNoWay() {
        new Bishop(Cell.D5).way(Cell.D5, Cell.D4);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenD5E5ThenNoWay() {
        new Bishop(Cell.D5).way(Cell.D5, Cell.E5);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenD5D6ThenNoWay() {
        new Bishop(Cell.D5).way(Cell.D5, Cell.D6);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenD5C5ThenNoWay() {
        new Bishop(Cell.D5).way(Cell.D5, Cell.C5);
    }

    @Test
    public void whenD5E4ThenWayE4() {
        Cell[] way = new Bishop(Cell.D5).way(Cell.D5, Cell.E4);
        Cell[] expected = new Cell[1];
        expected[0] = Cell.E4;
        assertArrayEquals(expected, way);
    }

    @Test
    public void whenD5E6ThenWayE6() {
        Cell[] way = new Bishop(Cell.D5).way(Cell.D5, Cell.E6);
        Cell[] expected = new Cell[1];
        expected[0] = Cell.E6;
        assertArrayEquals(expected, way);
    }

    @Test
    public void whenD5C6ThenWayC6() {
        Cell[] way = new Bishop(Cell.D5).way(Cell.D5, Cell.C6);
        Cell[] expected = new Cell[1];
        expected[0] = Cell.C6;
        assertArrayEquals(expected, way);
    }

    @Test
    public void whenD5C4ThenWayC4() {
        Cell[] way = new Bishop(Cell.D5).way(Cell.D5, Cell.C4);
        Cell[] expected = new Cell[1];
        expected[0] = Cell.C4;
        assertArrayEquals(expected, way);
    }

    @Test
    public void whenD5F3ThenWayE4F3() {
        Cell[] way = new Bishop(Cell.D5).way(Cell.D5, Cell.F3);
        Cell[] expected = new Cell[2];
        expected[0] = Cell.E4;
        expected[1] = Cell.F3;
        assertArrayEquals(expected, way);
    }

    @Test
    public void whenD5F7ThenWayE6F7() {
        Cell[] way = new Bishop(Cell.D5).way(Cell.D5, Cell.F7);
        Cell[] expected = new Cell[2];
        expected[0] = Cell.E6;
        expected[1] = Cell.F7;
        assertArrayEquals(expected, way);
    }

    @Test
    public void whenD5B7ThenWayC6B7() {
        Cell[] way = new Bishop(Cell.D5).way(Cell.D5, Cell.B7);
        Cell[] expected = new Cell[2];
        expected[0] = Cell.C6;
        expected[1] = Cell.B7;
        assertArrayEquals(expected, way);
    }

    @Test
    public void whenD5B3ThenWayC4B3() {
        Cell[] way = new Bishop(Cell.D5).way(Cell.D5, Cell.B3);
        Cell[] expected = new Cell[2];
        expected[0] = Cell.C4;
        expected[1] = Cell.B3;
        assertArrayEquals(expected, way);
    }
}
