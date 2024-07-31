package walking.game;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

import walking.game.WalkingBoardWithPlayers;
import walking.game.util.Direction;
import java.util.Arrays;

public class WalkingBoardWithPlayersTest {
    @Test
    public void walk1() {
        WalkingBoardWithPlayers wp = new WalkingBoardWithPlayers(5, 4);

        int[] stepCounts = {2, 1, 1, 3, 5, 4, 2, 4, 0, 1, 5, 3, 4, 2};
        int[][] expectedBoard = {
            { 3,  2,  3,  4,  5},
            { 3,  3,  3,  3, 12},
            {13,  3,  3,  3, 13},
            {13,  3,  3,  3, 13},
            {13, 13, 13, 13, 13}
        };
        int[] expectedScores = {0, 24, 12, 6};
        
        int[] scores = wp.walk(stepCounts);
        assertArrayEquals(expectedBoard, wp.getTiles());
        assertArrayEquals(expectedScores, scores);
    }

    @Test
    public void walk2() {
        int[][] arr = new int[4][4];
        for (int[] row : arr) Arrays.fill(row, 10);
        WalkingBoardWithPlayers wp = new WalkingBoardWithPlayers(arr, 3);

        int[] stepCounts = {3, 5, 4, 1, 3, 2, 2, 1, 4, 1, 1, 5};
        int[][] expectedBoard = {
            {13,  3,  4,  5},
            {13, 10, 10, 13},
            {13, 10, 10, 13},
            {13, 13, 13, 13}
        };
        int[] expectedScores = {20, 80, 20};
        
        int[] scores = wp.walk(stepCounts);
        assertArrayEquals(expectedBoard, wp.getTiles());
        assertArrayEquals(expectedScores, scores);
    }
}