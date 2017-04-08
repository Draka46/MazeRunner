package MazeRunner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public interface GameObject {

    void update(KeyCode keypressed);

    void draw(GraphicsContext graphicsContext, SceneInfo sceneInfo);
}
