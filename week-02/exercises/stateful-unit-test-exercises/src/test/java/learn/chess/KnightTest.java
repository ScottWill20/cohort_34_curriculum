package learn.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    Knight knight = new Knight();

    // cant move out of bounds

    @Test
    void shouldNotBeAbleToMoveOutOfBounds() {
        assertFalse(knight.move(-1,0)); // too far up
        assertEquals(0, knight.getRow());
        assertEquals(0, knight.getColumn());

        assertFalse(knight.move(8,0)); // too far up
        assertEquals(0, knight.getRow());
        assertEquals(0, knight.getColumn());

        assertFalse(knight.move(0,-1)); // too far up
        assertEquals(0, knight.getRow());
        assertEquals(0, knight.getColumn());

        assertFalse(knight.move(0,8)); // too far up
        assertEquals(0, knight.getRow());
        assertEquals(0, knight.getColumn());

    }

    // cant move to the same spot

    @Test
    void shouldNotBeAbleToMoveToSameSpot() {
        assertFalse(knight.move(0,0)); // too far up
        assertEquals(0, knight.getRow());
        assertEquals(0, knight.getColumn());

    }

    // make sure we can move after an invalid move

    @Test
    void shouldBeAbleToMoveAfterInvalidMove() {
        assertFalse(knight.move(1,1));
        assertEquals(0, knight.getRow());
        assertEquals(0, knight.getColumn());

        assertTrue(knight.move(2,1));
        assertEquals(2, knight.getRow());
        assertEquals(1, knight.getColumn());
    }

    // make sure we can't make invalid moves

    @Test
    void shouldNotBeAbleToMakeInvalidMove() {
        assertFalse(knight.move(1,3));
        assertFalse(knight.move(3,1));
        assertFalse(knight.move(0,2));
        assertFalse(knight.move(1,1));
        assertFalse(knight.move(0,1));
        assertFalse(knight.move(2,0));

    }

    // make sure we can make valid L moves

    @Test
    void shouldBeAbleToMakeValidMoves() {
        Knight knight = new Knight(3, 3);

        assertTrue(knight.move(4,5)); // down and big right
        assertEquals(4, knight.getRow());
        assertEquals(5, knight.getColumn());

        assertTrue(knight.move(3,3)); // up and big left
        assertEquals(3, knight.getRow());
        assertEquals(3, knight.getColumn());

        assertTrue(knight.move(4,1)); // down and big left
        assertEquals(4, knight.getRow());
        assertEquals(1, knight.getColumn());

        assertTrue(knight.move(3,3)); // up and big right
        assertEquals(3, knight.getRow());
        assertEquals(3, knight.getColumn());

        assertTrue(knight.move(5,4)); // big down and right
        assertEquals(5, knight.getRow());
        assertEquals(4, knight.getColumn());

        assertTrue(knight.move(3,3)); // big up and left
        assertEquals(3, knight.getRow());
        assertEquals(3, knight.getColumn());

        assertTrue(knight.move(5,2)); // big down and left
        assertEquals(5, knight.getRow());
        assertEquals(2, knight.getColumn());

        assertTrue(knight.move(3,3)); // big up and right
        assertEquals(3, knight.getRow());
        assertEquals(3, knight.getColumn());






    }





}