/**
 * Class type: Singular class (single instance)
 *
 * Description:
 *   - Represents the player character
 *   - Handles change in it's own position on the game screen and draws itself accordingly
 */

package MazeRunner;

import MazeRunner.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

import java.awt.*;

public class Jake implements GameObject{

    private int X;
    private int Y;
    private int old_X;          // X position, prior to update
    private int old_Y;          // Y position, prior to update
    private Color color;
    private Maze maze;

    private String direction;
    private Image graphics;

    public Jake(Point position, Maze maze)
    {
        X = position.x;
        Y = position.y;
        this.maze = maze;
        color = Color.BLUE;
        direction = "RIGHT";
    }

    public void update(KeyCode keyPressed) {
        old_X = X;
        old_Y = Y;

        // Updates position (blocks attempts to move beyond the game screen edge, as well as into walls)
        switch (keyPressed)
        {
            case DOWN:
                if (!((Y + 1) >= Controller.sceneInfo.getHeight()) && maze.isTileWalkAble(X, Y + 1)) {
                    Y++; direction = "DOWN"; }
                break;
            case LEFT:
                if (!((X - 1) < 0) && maze.isTileWalkAble(X - 1, Y)) {
                    X--; direction = "LEFT"; }
                break;
            case RIGHT:
                if (!((X + 1) >= Controller.sceneInfo.getWidth()) && maze.isTileWalkAble(X + 1, Y)) {
                    X++; direction = "RIGHT"; }
                break;
            case UP:
                if (!((Y - 1) < 0) && maze.isTileWalkAble(X, Y - 1)) {
                    Y--; direction = "UP"; }
                break;
        }
    }


    @Override
    public void draw(GraphicsContext g, SceneInfo sceneInfo) {

        g.clearRect(old_X * sceneInfo.getFieldWidth(), old_Y * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight());

        setGraphics();
        g.drawImage(graphics, this.getX() * sceneInfo.getFieldWidth(), this.getY() * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight());
    }

    public int getX() {
        return X;
    }

    public void setX(int x) { X = x; }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    void setGraphics() {
        switch (direction) {
            case "DOWN":
                graphics = new Image("MazeRunnerGraphics/pac_D.png");
                break;
            case "LEFT":
                graphics = new Image("MazeRunnerGraphics/pac_L.png");
                break;
            case "RIGHT":
                graphics = new Image("MazeRunnerGraphics/pac_R.png");
                break;
            case "UP":
                graphics = new Image("MazeRunnerGraphics/pac_U.png");
                break;
        }
    }
}