package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.*;

import static org.junit.Assert.*;

/**
 * BoardTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {
    @Test
    public void whenMoveD5E4ThenCorrect() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.E4);
        try {
            board.move(Cell.E4, Cell.F3);
        } catch (FigureNotFoundException fnfe) {
            fail("The figure hasn't been correctly moved");
        }
    }

    @Test
    public void whenMoveD5F3ThenCorrect() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.F3);
        try {
            board.move(Cell.F3, Cell.G2);
        } catch (FigureNotFoundException fnfe) {
            fail("The figure hasn't been correctly moved");
        }
    }

    @Test
    public void whenMoveD5E6ThenCorrect() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.E6);
        try {
            board.move(Cell.E6, Cell.F7);
        } catch (FigureNotFoundException fnfe) {
            fail("The figure hasn't been correctly moved");
        }
    }

    @Test
    public void whenMoveD5F7ThenCorrect() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.F7);
        try {
            board.move(Cell.F7, Cell.G8);
        } catch (FigureNotFoundException fnfe) {
            fail("The figure hasn't been correctly moved");
        }
    }

    @Test
    public void whenMoveD5C6ThenCorrect() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.C6);
        try {
            board.move(Cell.C6, Cell.B7);
        } catch (FigureNotFoundException fnfe) {
            fail("The figure hasn't been correctly moved");
        }
    }

    @Test
    public void whenMoveD5B7ThenCorrect() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.B7);
        try {
            board.move(Cell.B7, Cell.A8);
        } catch (FigureNotFoundException fnfe) {
            fail("The figure hasn't been correctly moved");
        }
    }

    @Test
    public void whenMoveD5C4ThenCorrect() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.C4);
        try {
            board.move(Cell.C4, Cell.B3);
        } catch (FigureNotFoundException fnfe) {
            fail("The figure hasn't been correctly moved");
        }
    }

    @Test
    public void whenMoveD5B3ThenCorrect() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.B3);
        try {
            board.move(Cell.B3, Cell.A2);
        } catch (FigureNotFoundException fnfe) {
            fail("The figure hasn't been correctly moved");
        }
    }

    @Test(expected = OccupiedWayException.class)
    public void whenMoveOnAnotherFigureThenOccupiedWayException() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.add(new Bishop(Cell.F3));
        board.move(Cell.D5, Cell.F3);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenMoveThroughAnotherFigureThenOccupiedWayException() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.add(new Bishop(Cell.F3));
        board.move(Cell.D5, Cell.G2);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveD5D4ThenImpossible() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.D4);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveD5D3ThenImpossible() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.D3);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveD5E5ThenImpossible() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.E5);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveD5F5ThenImpossible() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.F5);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveD5D6ThenImpossible() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.D6);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveD5D7ThenImpossible() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.D7);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveD5C5ThenImpossible() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.C5);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveD5B5ThenImpossible() {
        Board board = new Board();
        board.add(new Bishop(Cell.D5));
        board.move(Cell.D5, Cell.B5);
    }
}
