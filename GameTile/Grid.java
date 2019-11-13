/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

/**
 * @author TA
 */
public class Grid {
    private static final int gridHeight = 9;
    private static final int gridWidth = 13;
    private static final int gridSlotSize = 64;
    private GameTile[][] gameGrid;


    public Grid(int[][] level) {
        gameGrid = new GameTile[gridWidth][gridHeight];
        initializeLevel(level);
    }

    public void initializeLevel(int[][] level) { /// if 0 tower grid, if 1 enemy grid
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                if (level[i][j] == 0)
                    gameGrid[i][j] = new Mountain();
                if (level[i][j] == 1)
                    gameGrid[i][j] = new Road();
            }
        }
    }

    //GETTERS

    public GameTile getGridSlot(int i, int j) {
        return gameGrid[i][j];
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridSlotSize() {
        return gridSlotSize;
    }
}
