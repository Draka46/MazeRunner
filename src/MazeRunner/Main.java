/** Partial Hand-In (Snake-based Maze game) - By: Mark Harder Andersen
 *
 * Assignment:
 *   - Create a game based on the snake game that was made in class, containing a maze with walls that the player
 *   character cannot transverse.
 *
 * Description:
 *   - The game runs following the same general logic as the snake game, as it was presented to function in class.
 *     - "Food" items, item spawning, movement restrictions and self collision mechanics have been removed
 *
 *     - A maze is generated based on input in the source code, in the form of a sequence of binary
 *       - The binary text accounts for all 30x20 tiles of the game screen
 *       - "0" represents tiles that the player character can walk on. "1", tiles that they cannot (in other words, walls)
 *
 *     - Non-walkable tiles are by default drawn on the canvas (once)
 *     - Player character ("Jake") clears and re-draws itself, as it updates
 *
 * Further Development:
 *  - Game Mechanics
 *    - Implement game goal
 *
 *    - Make the maze invisible, and/or have walls only appear when the player character walks into them / are visible
 *    while the player character is within a certain area.
 *
 *    - Make it so the maze changes with time, making it so the player has to solve it in a time limit
 *
 *  - Source Code
 *    - Refinements
 *      - Consistent coding habits (private/public state of variables, etc.)
 *
 * Notes:
 *   - The SnakeFX soure code provided in class, was used as the initial template
 */

package MazeRunner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("JakeGame");

        MazeRunner.Controller controller = (MazeRunner.Controller) loader.getController();

        Scene scene = new Scene(root, 600,400);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) { controller.keyPressed(event.getCode()); }
        });

        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("MazeRunner/stylesheet.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}