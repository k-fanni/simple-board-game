package walking.game.player;

import walking.game.util.Direction;

public class Player {
    private int score;
    protected Direction direction = Direction.UP;

    public Player() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public Direction getDirection() {
        return direction;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public void turn() {
        switch (direction) {
            case Direction.UP:
                direction = Direction.RIGHT;
                break;
            case Direction.RIGHT:
                direction = Direction.DOWN;
                break;
            case Direction.DOWN:
                direction = Direction.LEFT;
                break;
            default:
                direction = Direction.UP;
                break;
        }
    }
}