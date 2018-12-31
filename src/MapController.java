import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Observable;
import java.util.Observer;

/**
 * Creates a MapController that loads and displays the tile map on the screen.
 */
class MapController implements Observer {
    private PApplet felineko;

    /**
     * Images of each background tile.
     */
    private PImage[][] sprites;

    private Map map;

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
        PImage tileMap = felineko.loadImage("TileMap3.png");
        PVector dimensions = new PVector(tileMap.height, tileMap.width);
        Map newMap = new Map(tileMap.height, tileMap.width);
        for (int i = 0; i < dimensions.y; i++){
            for (int j = 0; j < dimensions.x; j++) {
                String type = Integer.toString(tileMap.get(i, j));
                Tile newTile = tileFactory.createTile(type, i, j);
                newMap.setTile(newTile);
                newTile.addObserver(this);
                System.out.println(type);
            }
        }
        this.map = newMap;
        return newMap;
    }

    /**
     * Preloads the sprites of the tiles from the Map.
     *
     * @param map Map with sprite locations.
     */
    void loadMap(Map map){
        sprites = new PImage[map.getColumns()][map.getRows()];
        for (int i = 0; i < map.getColumns(); i++){
            for (int j = 0; j < map.getRows(); j++) {
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
        for (int i = 0; i < map.getColumns(); i++){
            for (int j = 0; j < map.getRows(); j++) {
                felineko.image(sprites[i][j], i*30, j*30);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Tile tile = (Tile) o;
        int x = tile.getX();
        int y = tile.getY();
        sprites[x][y] = felineko.loadImage(map.getTile(x, y).getType()+".png");
    }
}