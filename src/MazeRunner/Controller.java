/**
 * Class type: Singular class (single instance)
 *
 * Description:
 *   - Handles relationship between Source Code and ScreenBuilder UI
 *   - Handles the setup, initializing and updating of the game components
 */

package MazeRunner;

import MazeRunner.*;
import MazeRunner.Tile;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.*;

public class Controller {

    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;

    public static SceneInfo sceneInfo;
    public static Maze maze;

    private float refreshRate = 300;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    public static ArrayList<Tile> tileList = new ArrayList<Tile>();

    public static Jake jake;

    public void btnStartAction(ActionEvent event)
    {
        System.out.println("btn clicked");
        labelStatus.setText("test");
        drawCanvas();
    }

    /** Executed when JavaFX is initialized. Used to setup the Snake game */
    public void initialize()
    {
        sceneInfo = new SceneInfo(canvas); // Contains and organizes screen-related information

        maze = new Maze(sceneInfo, this); // Sets up the maze

        jake = new Jake(new Point(1, 1), maze); // Sets up the player character "Jake"
        gameObjects.add(jake);

        Enemy enemy = new Enemy(Color.RED, 31, 30);
        gameObjects.add(enemy);

        // Draws Maze (once and only once)
        GraphicsContext g = canvas.getGraphicsContext2D();
        for (Tile tile : tileList)
        {
            if (tile.walkAble == false) {
                tile.draw(g, sceneInfo);
            }
        }

        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now)
            {
                if (now > lastUpdate + refreshRate * 1000000)
                {
                    lastUpdate = now;
                    update(now);
                }
            }
        }.start();
    }

    public void addTile(Tile tile) {
        tileList.add(tile);
    }

    public void keyPressed(KeyCode keyCode)
    {
        this.keyPressed = keyCode;
    }

    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */
    private void update(long now)
    {
        for (int i = 0; i < gameObjects.size() ; i++) {
            gameObjects.get(i).update(keyPressed);
        }

        drawCanvas();
    }


    /** Draws the game objects (currently ONLY Jake) - used in the gameloop */
    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        // draw gameObjects
        for (GameObject item : gameObjects)
        {
            item.draw(g, sceneInfo);
        }
    }
}