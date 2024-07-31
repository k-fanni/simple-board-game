package walking.game.player;

public class MadlyRotatingBuccaneer extends Player {
    private int score;
    private int turnCount;

    public MadlyRotatingBuccaneer() {
        super();
        turnCount = 0;
    }

    @Override public void turn() {
        for (int i = 0; i < turnCount; i++) {
            super.turn();
        }
        turnCount++;
    }
}