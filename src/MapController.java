import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Creates a MapController that loads and displays the tile map on the screen.
 */
public class MapController extends PApplet {
    /**
     * Images of each background tile.
     */
    PImage[][] sprites;

    /**
     * Returns a map specified by a .png file.
     *
     * @return map with Tiles and their locations.
     */
    public Map setUpMap() {
        TileFactory tileFactory = new TileFactory();
        PImage tileMap = loadImage("TileMap.png");
        PVector dimensions = new PVector(tileMap.height, tileMap.width);
        Map newMap = new Map(tileMap.height, tileMap.width);
        for (int i = 0; i < dimensions.x; i++){
            for (int j = 0; j < dimensions.y; j++) {
                String type = Integer.toString(tileMap.get(i, j));
                Tile newTile = tileFactory.createTile(type, i, j);
                newMap.setTile(newTile);
            }
        }
        return newMap;
    }
}