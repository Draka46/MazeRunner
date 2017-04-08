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

    public Jake(Point position, Maze maze)
    {
        X = position.x;
        Y = position.y;
        color = Color.BLUE;
        this.maze = maze;
    }

    public void update(KeyCode keyPressed) {
        old_X = X;
        old_Y = Y;

        // Updates position (blocks attempts to move beyond the game screen edge, as well as into walls)
        switch (keyPressed)
        {
            case DOWN:
                if (!((Y + 1) >= Controller.sceneInfo.getHeight()) && maze.isTileWalkAble(X, Y + 1)) { Y++; }
                break;
            case LEFT:
                if (!((X - 1) < 0) && maze.isTileWalkAble(X - 1, Y)) { X--; }
                break;
            case RIGHT:
                if (!((X + 1) >= Controller.sceneInfo.getWidth()) && maze.isTileWalkAble(X + 1, Y)) { X++; }
                break;
            case UP:
                if (!((Y - 1) < 0) && maze.isTileWalkAble(X, Y - 1)) { Y--; }
                break;
        }
    }


    @Override
    public void draw(GraphicsContext g, SceneInfo sceneInfo) {

        g.clearRect(old_X * sceneInfo.getFieldWidth(), old_Y * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight());

        // g.setFill(color);
        // g.fillRoundRect(this.getX() * sceneInfo.getFieldWidth(), this.getY() * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight(), 3, 3);

        Image test = new Image("MazeRunnerGraphics/pac_R.png");
        g.drawImage(test, this.getX() * sceneInfo.getFieldWidth(), this.getY() * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight());
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
}
