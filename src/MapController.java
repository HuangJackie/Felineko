import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
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
    private final PVector[] doorLocation = new PVector[3];

    MapController(PApplet felineko){
        this.felineko = felineko;
//        doorLocation[0] = new PVector()
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
        ArrayList<DoorTile> doors = new ArrayList<>();
        for (int i = 0; i < dimensions.y; i++){
            for (int j = 0; j < dimensions.x; j++) {
                String type = Integer.toString(tileMap.get(i, j));
                Tile newTile = tileFactory.createTile(type, i, j);
                newMap.setTile(newTile);
                newTile.addObserver(this);
                if (newTile.getType().equals("DOOR")){
                    doors.add((DoorTile) newTile);
                }
            }
        }
        newMap.setDoor(doors);
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

    public void removeDoor(){

    }

    @Override
    public void update(Observable o, Object arg) {
        Tile tile = (Tile) o;
        int x = tile.getX();
        int y = tile.getY();
        sprites[x][y] = felineko.loadImage(map.getTile(x, y).getType()+".png");
    }
}