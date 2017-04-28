package MazeRunner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import static MazeRunner.Controller.jake;
import static MazeRunner.Controller.maze;
import static MazeRunner.Controller.sceneInfo;

public class Enemy implements GameObject {
    private Color Color;
    private int X;
    private int Y;
    private int old_X;
    private int old_Y;
    private String direction;
    private Image graphics;

    public Enemy(Color color, int x, int y) {
        Color = color;
        X = x;
        Y = y;
    }

    public void update(KeyCode keyPressed) {
        A_star();

        switch (keyPressed)
        {
            case DOWN:
                if (!((Y + 1) >= Controller.sceneInfo.getHeight())) {
                    Y++; direction = "DOWN"; }
                break;
            case LEFT:
                if (!((X - 1) < 0)) {
                    X--; direction = "LEFT"; }
                break;
            case RIGHT:
                if (!((X + 1) >= Controller.sceneInfo.getWidth())) {
                    X++; direction = "RIGHT"; }
                break;
            case UP:
                if (!((Y - 1) < 0)) {
                    Y--; direction = "UP"; }
                break;
        }
        // if () { direction = "M2"; }
    }

    void A_star() {
        boolean pathFound = false;
        double[][] grid = new double[sceneInfo.getWidth()][sceneInfo.getHeight()];
        int i = 1;

        while (!pathFound) {

            for (int j = 0; j < 8*(i+1); j++) {

            }

            i++;
        }
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