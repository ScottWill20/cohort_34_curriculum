package learn.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    Queen queen = new Queen();

    @Test
    void shouldMoveToFourCorners() {
        assertTrue(queen.move(7, 0)); // top left;
        assertEquals(7, queen.getRow());
        assertEquals(0, queen.getColumn());

        assertTrue(queen.move(7, 7)); // top right;
        assertEquals(7, queen.getRow());
        assertEquals(7, queen.getColumn());

        assertTrue(queen.move(0, 7)); // bottom right;
        assertEquals(0, queen.getRow());
        assertEquals(7, queen.getColumn());

        assertTrue(queen.move(0, 0)); // bottom left;
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());
    }

    // 1. Add tests to validate Queen movement.
    // Required tests:




    // Always confirm that fields have been properly updated using getters.

    // - anything off the board is invalid, should return false and leave field values alone.
    @Test
    void shouldNotMoveOutOfBounds() {
        // assert
        assertFalse(queen.move(-1,0)); // trying to move up too far
        assertEquals(0,queen.getRow());
        assertEquals(0,queen.getColumn());

        assertFalse(queen.move(8,0)); // trying to move up too far
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());

        assertFalse(queen.move(0,-1));
        assertEquals(0,queen.getRow());
        assertEquals(0,queen.getColumn());

        assertFalse(queen.move(0,8)); // trying to move up too far
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());

    }

    // - moving to the current location should return false and leave field values alone.
    @Test
    void shouldNotBeAbleToMoveToSameSpot() {
        assertFalse(queen.move(0,0));
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());

    }

    // - should still be able to move after an invalid move.

    @Test
    void shouldStillBeAbleToMoveAfterInvalidMove() {
        assertFalse(queen.move(-7,2));
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());
        assertTrue(queen.move(7,0));
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());
    }

    // - can move diagonally in various ways
    // happy path - validate correct movement

    @Test
    void shouldBeAbleToMoveUpDownRightAndLeft() {
        assertTrue(queen.move(3,3)); // move down and right
        assertEquals(3, queen.getRow());
        assertEquals(3, queen.getColumn());

        assertTrue(queen.move(2,2)); // move up and left
        assertEquals(2, queen.getRow());
        assertEquals(2, queen.getColumn());

        assertTrue(queen.move(1,3)); // move up and right
        assertEquals(1, queen.getRow());
        assertEquals(3, queen.getColumn());

        assertTrue(queen.move(4,0)); // move down and left
        assertEquals(4, queen.getRow());
        assertEquals(0, queen.getColumn());
    }

    // unhappy path - validate that incorrect movement fails

    @Test
    void shouldNotBeAbleToMakeIncorrectDiagonalMovement() {
        assertFalse(queen.move(1,2)); // cant move row less than column
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());

        assertFalse(queen.move(4,2)); // cant move row greater than column
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());

    }

}