import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Creates a MapController that loads and displays the tile map on the screen.
 */
class MapController implements Observer {
    /**
     * The current PApplet of the game.
     */
    private PApplet felineko;

    /**
     * Images of each background tile.
     */
    private PImage[][] sprites;

    private Map map = new Map(0, 0);

    /**
     * Creates a new MapController to manage drawing and updating location of the Tiles.
     *
     * @param felineko the PApplet of the main sketch
     */
    MapController(PApplet felineko) {
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
        Map newMap = new Map(tileMap.height, tileMap.width);
        ArrayList<DoorTile> doors = new ArrayList<>();
        for (int i = 0; i < tileMap.width; i++) {
            for (int j = 0; j < tileMap.height; j++) {
                String type = Integer.toString(tileMap.get(i, j));
                Tile newTile = tileFactory.createTile(type, i, j);
                newMap.setTile(newTile);
                if (newTile.getType().equals(TileFactory.DOOR)) {
                    doors.add((DoorTile) newTile);
                }
            }
        }
        newMap.setDoor(doors);
        if (sprites == null) {
            sprites = new PImage[newMap.getColumns()][newMap.getRows()];
        }
        return newMap;
    }

    /**
     * Add MapController as the observer of each Tile.
     */
    void addObservers() {
        for (int i = 0; i < map.getColumns(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                map.getTile(i, j).addObserver(this);
            }
        }
    }

    /**
     * Preloads the sprites of the Tiles from the Map.
     *
     * @param map Map with sprite locations.
     */
    void loadMap(Map map) {
        Tile[][] oldTiles = this.map.getTiles();
        Tile[][] newTiles = map.getTiles();
        for (int i = 0; i < map.getColumns(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                if (sprites[i][j] == null || !oldTiles[i][j].getType().equals(newTiles[i][j].getType())) {
                    sprites[i][j] = felineko.loadImage(map.getTile(i, j).getType() + ".png");
                }
            }
        }
        this.map = map;
    }

    /**
     * Draws the Map sprites to the screen.
     *
     * @param map Map with sprite Locations.
     */
    void drawMap(Map map) {
        for (int i = 0; i < map.getColumns(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                felineko.image(sprites[i][j], i * 30, j * 30);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Tile tile = (Tile) o;
        int x = tile.getX();
        int y = tile.getY();
        sprites[x][y] = felineko.loadImage(map.getTile(x, y).getType() + ".png");
    }
}