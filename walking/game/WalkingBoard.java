package walking.game;

import java.util.Arrays;
import walking.game.util.Direction;

public class WalkingBoard {
    private int[][] tiles;
    private int x;
    private int y;
    public static final int BASE_TILE_SCORE = 3;

    public WalkingBoard(int size) {
        makeBoard(size);
    }

    public WalkingBoard(int[][] tiles) {
        int max = tiles.length;
        for (int i = 0; i < tiles.length; i++) {
            max = Math.max(tiles[i].length, max);
        }

        makeBoard(max);

        // a paraméterként kapott tiles tömb elemein kell végigmennünk, hogy
        // milyen értékek kerülhetnek belőle az újonnan készített táblára
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                this.tiles[i][j] = Math.max(tiles[i][j], this.tiles[i][j]);
            }
        }
    }

    private void makeBoard(int size) {
        tiles = new int[size][size];
        for (int[] row : tiles) {
            Arrays.fill(row, BASE_TILE_SCORE);
        }
        x = 0;
        y = 0;
    }

    public int[] getPosition() {
        int[] position = {x, y};
        return position;
    }

    public boolean isValidPosition(int x, int y) {
        if (x < 0 || x >= tiles.length || y < 0 || y >= tiles.length) {
            return false;
        }
        return true;
    }

    public int getTile(int x, int y) {
        if (!isValidPosition(x, y)) throw new IllegalArgumentException();
        return tiles[x][y];
    }

    public int[][] getTiles() {
        int[][] tilesCopy = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tilesCopy[i][j] = tiles[i][j];
            }
        }
        return tilesCopy;
    }

    public static int getXStep(Direction direction) {
        switch (direction) {
            case Direction.UP:
                return -1;
            case Direction.DOWN:
                return 1;
            default:
                return 0;
        }
    }

    public static int getYStep(Direction direction) {
        switch (direction) {
            case Direction.LEFT:
                return -1;
            case Direction.RIGHT:
                return 1;
            default:
                return 0;
        }
    }

    public int moveAndSet(Direction direction, int value) {
        int newX = x + getXStep(direction);
        int newY = y + getYStep(direction);

        if (!isValidPosition(newX, newY)) return 0;

        int oldValue = getTile(newX, newY);
        x = newX;
        y = newY;
        tiles[x][y] = value;
        return oldValue;
    }
}