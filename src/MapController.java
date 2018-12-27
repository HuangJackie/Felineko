import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Creates a MapController that loads and displays the tile map on the screen.
 */
class MapController {
    private PApplet felineko;

    /**
     * Images of each background tile.
     */
    private PImage[][] sprites;

    MapController(PApplet felineko){
        this.felineko = felineko;
    }

    /**
     * Returns a map specified by a .png file.
     *
     * @return map with Tiles and their locations.
     */
    Map setUpMap() {
        TileFactory tileFactory = new TileFactory();
        PImage tileMap = felineko.loadImage("TileMap.png");
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

    /**
     * Preloads the sprites of the tiles from the Map.
     *
     * @param map Map with sprite locations.
     */
    void loadMap(Map map){
        sprites = new PImage[map.getRows()][map.getColumns()];
        for (int i = 0; i < map.getRows(); i++){
            for (int j = 0; j < map.getColumns(); j++) {
                sprites[i][j] = felineko.loadImage(map.getTile(i, j).getType()+".png");
                sprites[i][j].resize(100,0);
            }
        }
    }

    /**
     * Draws the map sprites to the screen.
     *
     * @param map Map with sprite Locations.
     */
    void drawMap(Map map){
        for (int i = 0; i < map.getRows(); i++){
            for (int j = 0; j < map.getColumns(); j++) {
                felineko.image(sprites[i][j], i*30, j*30);
            }
        }
    }
}