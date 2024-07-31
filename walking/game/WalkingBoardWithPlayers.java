package walking.game;

import walking.game.player.*;

public class WalkingBoardWithPlayers extends WalkingBoard {
    private Player[] players;
    private int round;
    public static final int SCORE_EACH_STEP = 13;

    public WalkingBoardWithPlayers(int[][] board, int playerCount) {
        super(board);
        initialize(playerCount);
    }

    public WalkingBoardWithPlayers(int size, int playerCount) {
        super(size);
        initialize(playerCount);
    }

    private void initialize(int playerCount) {
        initPlayers(playerCount);
        round = 0;
    }

    private void initPlayers(int playerCount) {
        if (playerCount < 2) throw new IllegalArgumentException();
        players = new Player[playerCount];
        players[0] = new MadlyRotatingBuccaneer();
        for (int i = 1; i < playerCount; i++) {
            players[i] = new Player();
        }
    }

    public int[] walk(int... stepCounts) {
        int i = 0;
        int steps = 0;
        for (int n = 0; n < stepCounts.length; n++) {
            if (i % players.length == 0) round++;
            players[i].turn();
            for (int m = 0; m < stepCounts[n]; m++) {
                int value = moveAndSet(players[i].getDirection(), steps);
                players[i].addToScore(value);
                steps = Math.min(steps + 1, SCORE_EACH_STEP);
            }
            i = (i + 1) % players.length;
        }

        int[] scores = new int[players.length];
        for (int j = 0; j < players.length; j++) {
            scores[j] = players[j].getScore();
        }
        return scores;
    }
}