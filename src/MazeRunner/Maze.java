/**
 * Class type: Singular class (single instance)
 *
 * Description:
 *   - Translates a binary identification of in-game "walls" into a matrix of Tile objects
 */

package MazeRunner;

import MazeRunner.Controller;

public class Maze {

    String mazeInBinary;
    public static Tile[][] tileList; // Matrix of every game object position, on the game screen

    public Maze(SceneInfo sceneInfo, Controller controller) {
        mazeInBinary = "000001000000000000000000000000000101001011111000010001000010011101001010000000011111111110000101001010111111110000000010011101001010000000000101111011010000001011111111111101001000011111111000010100000101001110000000001011110101011101000000111111101000010101010001011111000000001111010111010111110001011101000000010010010000000101010001111111010010111111110101010101000000010010100000010101010101011111110010101110010101010101000000000010101010111101010101111111111010100010000100010100000100000010101111111110010101111101001000000000000011110100000101111010111111111000000101110100100010000000001000";

        Controller callingController = controller;
        tileList = new Tile[Controller.sceneInfo.getWidth()][Controller.sceneInfo.getHeight()];

        // Uses the binary information, as basis for what tiles are walkable by "Jake"
        for (int i = 0; i < sceneInfo.getHeight(); i++) {
            for (int j = 0; j < sceneInfo.getWidth(); j++) {
                boolean walkAble = false;
                char positionChar = mazeInBinary.charAt(j + (i*30));
                char walkAbleChar = '0';

                if (positionChar == walkAbleChar) { walkAble = true; }

                Tile tile = new Tile(j, i, walkAble);
                tileList[j][i] = tile;
                callingController.addTile(tile);
            }
        }
    }

    public boolean isTileWalkAble(int x, int y) { return tileList[x][y].walkAble; }
}
