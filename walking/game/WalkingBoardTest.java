package walking.game;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

import walking.game.WalkingBoard;
import walking.game.util.Direction;
import java.util.Arrays;

public class WalkingBoardTest {
    @ParameterizedTest
    @CsvSource(textBlock = """
        1
        2
        3
        10
    """)
    @DisableIfHasBadStructure
    public void testSimpleInit(int size) {
        WalkingBoard board = new WalkingBoard(size);
        assertEquals(size, board.getTiles().length);
        assertEquals(size, board.getTiles()[0].length);
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(0, 0));
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(0, size - 1));
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(size - 1, 0));
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(size - 1, size - 1));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
          1, 2,   1
          2, 1,   2
          3, 5,   3
          5, 3,   4
        100, 2, 101
    """)
    @DisableIfHasBadStructure
    public void testCustomInit(int x, int y, int expected) {
        int[][] arr = new int[x][y];
        for (int[] row : arr) Arrays.fill(row, expected);
        WalkingBoard board = new WalkingBoard(arr);

        expected = Math.max(expected, WalkingBoard.BASE_TILE_SCORE);
        //int min = Math.min(x, y) - 1;

        assertEquals(expected, board.getTile(x - 1, y - 1));

        arr[x - 1][y - 1] = -3;
        assertEquals(expected, board.getTile(x - 1, y - 1));

        int[][] newArr = board.getTiles();
        newArr[x - 1][y - 1] = 1000;
        assertEquals(expected, board.getTile(x - 1, y - 1));
    }

    @Test
    public void testMoves() {
        int[][] arr = new int[5][5];
        for (int[] row : arr) Arrays.fill(row, 25);
        WalkingBoard board = new WalkingBoard(arr);
        
        assertEquals(Math.max(arr[0][1], WalkingBoard.BASE_TILE_SCORE), board.moveAndSet(Direction.RIGHT, 100));
        assertEquals(100, board.getTile(0, 1));

        assertEquals(Math.max(arr[1][1], WalkingBoard.BASE_TILE_SCORE), board.moveAndSet(Direction.DOWN, 80));
        assertEquals(80, board.getTile(1, 1));

        assertEquals(Math.max(arr[1][0], WalkingBoard.BASE_TILE_SCORE), board.moveAndSet(Direction.LEFT, 70));
        assertEquals(70, board.getTile(1, 0));

        assertEquals(Math.max(arr[0][0], WalkingBoard.BASE_TILE_SCORE), board.moveAndSet(Direction.UP, 60));
        assertEquals(60, board.getTile(0, 0));

        assertEquals(0, board.moveAndSet(Direction.LEFT, 50));
        assertEquals(60, board.getTile(0, 0));
    }
}