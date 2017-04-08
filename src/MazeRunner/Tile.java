/**
 * Class type: Pre-destined class (multiple instance)
 *
 * Description:
 *   - Represents a game object sized space on the game screen
 *   - Contains information about whether the space can be walked onto, but the player character
 */

package MazeRunner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile{
    private Color color = Color.BLACK;
    private int X;
    private int Y;
    public boolean walkAble;

    public Tile(int x, int y, boolean walkAble) {
        X = x;
        Y = y;
        this.walkAble = walkAble;
    }

    public void draw(GraphicsContext g, MazeRunner.SceneInfo sceneInfo) {
        g.setFill(color);
        g.fillRoundRect(X * sceneInfo.getFieldWidth(), Y * sceneInfo.getFieldHeight(), sceneInfo.getFieldWidth(), sceneInfo.getFieldHeight(), 3, 3);
    }
}
