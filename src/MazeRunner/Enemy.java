package MazeRunner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

import static MazeRunner.Controller.*;

public class Enemy implements GameObject {
    private boolean allowDiagMov = false;   // Setup variable: Determines if enemies should be able to move diagonally
    private int nextTileAInt;
    private Color Color;
    private int X;
    private int Y;
    private int old_X;
    private int old_Y;
    private String direction = "RIGHT";
    private Image graphics;
    Jake jake;

    public Enemy(Color color, int x, int y, Jake jake) {
        if (allowDiagMov)
            nextTileAInt = 8;
        else
            nextTileAInt = 4;

        Color = color;
        X = x;
        Y = y;
        this.jake = jake;
    }

    public void update() {
        A_star();

        /*
        switch (direction)
        {
            case "DOWN":
                if (!((Y + 1) >= Controller.sceneInfo.getHeight())) {
                    Y++; direction = "DOWN"; }
                break;
            case "LEFT":
                if (!((X - 1) < 0)) {
                    X--; direction = "LEFT"; }
                break;
            case "RIGHT":
                if (!((X + 1) >= Controller.sceneInfo.getWidth())) {
                    X++; direction = "RIGHT"; }
                break;
            case "UP":
                if (!((Y - 1) < 0)) {
                    Y--; direction = "UP"; }
                break;
        }
        */

        // if () { direction = "M2"; }
    }

    public void update(KeyCode keyPressed) {
        update();
    }

    void A_star() {
        boolean pathFound = false;
        ArrayList<Tile> openList = new ArrayList<>();
        ArrayList<Tile> closedList = new ArrayList<>();
        ArrayList<Tile> path = new ArrayList<>();
        int[][] fCostList = new int[Maze.matrixLength][Maze.matrixHeight];

        closedList.add(Maze.tileList[X][Y]);

        // Generates the A* algorithmic path (adjacent tile to goal tile)
        while (!pathFound) {
            // Update the open list
            ArrayList<Tile> tempOpenList = new ArrayList<>(openList);
            for (int i = 0; i < closedList.size(); i++) {
                Tile tile = closedList.get(i);

                for (int j = 0; j < nextTileAInt; j++) {
                    Tile tileA = nextTileA(j, tile);           // Adjacent tiles

                    if (tile.X != tileA.X || tile.Y != tileA.Y) {
                        if (!tempOpenList.contains(tileA) && !closedList.contains(tileA))
                            tempOpenList.add(tileA);

                        int h_Cost = Math.abs(jake.getX() - tileA.X); // 'Cost' (distance) from tile, to goal (h_Cost + g_Cost)
                        int g_Cost = Math.abs(jake.getY() - tileA.Y); // Horizonal distance to goal
                        int f_Cost = h_Cost + g_Cost;                 // Vertical distance to goal

                        if (fCostList[tileA.X][tileA.Y] == 0)
                            fCostList[tileA.X][tileA.Y] = f_Cost;
                        else if (f_Cost < fCostList[tileA.X][tileA.Y])
                            fCostList[tileA.X][tileA.Y] = f_Cost;
                    }
                }
            }
            openList = new ArrayList<>(tempOpenList);

            // Update the closed list
            int bestF_Cost = Integer.MAX_VALUE;
            Tile bestTileA = null;

            for (int i = 0; i < openList.size(); i++) {
                Tile tile = openList.get(i);
                int temp = 0;
                temp = fCostList[tile.X][tile.Y];
                if ((temp != 0 && temp < bestF_Cost) || (tile.X == jake.getX() && tile.Y == jake.getY())) {
                    bestF_Cost = temp;
                    bestTileA = tile;
                }
            }
            closedList.add(bestTileA);
            openList.remove(bestTileA);

            // Check for whether beta-path construction is finished
            for (int i = 0; i < closedList.size(); i++) {
                Tile tile = closedList.get(i);

                if (tile.X == jake.getX() && tile.Y == jake.getY())
                    pathFound = true;
            }
        }

        // Construct functional path (from goal tile to adjacent), removing unnecessary tiles
        pathFound = false;
        Collections.reverse(closedList);
        path.add(closedList.get(0));

        for (int i = 0; i < path.size(); i++) {
            int bestF_Cost = Integer.MAX_VALUE;
            Tile bestTileA = null;
            Tile tile = path.get(i);

            for (int j = 0; j < nextTileAInt; j++) {
                Tile tileA = nextTileA(j, tile);

                if (tile.X != tileA.X || tile.Y != tileA.Y) {
                    if (!path.contains(tileA) && closedList.contains(tileA)) {

                        int h_Cost = Math.abs(X - tileA.X);
                        int g_Cost = Math.abs(Y - tileA.Y);
                        int f_Cost = h_Cost + g_Cost;

                        if (f_Cost < bestF_Cost) {
                            bestF_Cost = f_Cost;
                            bestTileA = tileA;
                        }
                    }
                }
            }

            if (bestTileA.X == X && bestTileA.Y == Y) {
                pathFound = true;
            }

            if (bestTileA != null && !pathFound) {
                if (bestTileA.X != jake.getX() || bestTileA.Y != jake.getY())
                    path.add(bestTileA);
            }
        }

        int test = 0;
    }

    /** Returns an adjacent tile, based on input */
    Tile nextTileA(int i, Tile tile) {
        switch (i)
        {
            case 0:
                if (tile.X+1 != sceneInfo.getWidth()) {               // Checks that adjacent tile isn't beyond the map boundary
                    if (Maze.isTileWalkAble(tile.X + 1, tile.Y))   // Checks that the adjacent tile isn't a wall
                        return Maze.tileList[tile.X + 1][tile.Y];
                }
                break;
            case 1:
                if (tile.Y-1 > 0) {
                    if (Maze.isTileWalkAble(tile.X, tile.Y - 1))
                        return Maze.tileList[tile.X][tile.Y - 1];
                }
                break;
            case 2:
                if (tile.X-1 > 0) {
                    if (Maze.isTileWalkAble(tile.X - 1, tile.Y))
                        return Maze.tileList[tile.X - 1][tile.Y];
                }
                break;
            case 3:
                if (tile.Y+1 != sceneInfo.getWidth()) {
                    if (Maze.isTileWalkAble(tile.X, tile.Y + 1))
                        return Maze.tileList[tile.X][tile.Y + 1];
                }
                break;
            case 4:
                if (tile.X+1 != sceneInfo.getWidth() && tile.Y+1 != sceneInfo.getHeight()) {
                    if (Maze.isTileWalkAble(tile.X + 1, tile.Y + 1))
                        return Maze.tileList[tile.X + 1][tile.Y + 1];
                }
                break;
            case 5:
                if (tile.X-1 > 0 && tile.Y+1 != sceneInfo.getHeight()) {
                    if (Maze.isTileWalkAble(tile.X - 1, tile.Y + 1))
                        return Maze.tileList[tile.X - 1][tile.Y + 1];
                }
                break;
            case 6:
                if (tile.X-1 > 0 && tile.Y-1 > 0) {
                    if (Maze.isTileWalkAble(tile.X - 1, tile.Y - 1))
                        return Maze.tileList[tile.X - 1][tile.Y - 1];
                }
                break;
            case 7:
                if (tile.X+1 != sceneInfo.getWidth() && tile.Y-1 > 0) {
                    if (Maze.isTileWalkAble(tile.X + 1, tile.Y - 1))
                        return Maze.tileList[tile.X + 1][tile.Y - 1];
                }
                break;
        }

        return tile;
    }

    public void draw(GraphicsContext g, SceneInfo sceneInfo) {
        g.clearRect(old_X * sceneInfo.getFieldWidth(), old_Y * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight());

        setGraphics();
        g.drawImage(graphics, this.getX() * sceneInfo.getFieldWidth(), this.getY() * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight());
    }

    void setGraphics() {
        switch (direction) {
            case "DOWN":
                graphics = new Image("MazeRunnerGraphics/enemy_D.png");
                break;
            case "LEFT":
                graphics = new Image("MazeRunnerGraphics/enemy_L.png");
                break;
            case "RIGHT":
                graphics = new Image("MazeRunnerGraphics/enemy_R.png");
                break;
            case "UP":
                graphics = new Image("MazeRunnerGraphics/enemy_U.png");
                break;
            case "M2":
                graphics = new Image("MazeRunnerGraphics/enemy_M2.png");
        }
    }

    public Color getColor() {
        return Color;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}