import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates a Map of Tile Objects to be displayed on the gamescreen.
 */
class Map implements Serializable {

    /**
     * All of the Tile objects in the Game.
     */
    private Tile[][] tileLocations;

    /**
     * Number of rows of Tile objects.
     */
    private int rows;

    /**
     * Number of columns of Tile objects.
     */
    private int columns;

    /**
     * List of the DoorTiles.
     */
    private ArrayList<DoorTile> doors = new ArrayList<>();

    static final long serialVersionUID = 6469620024832591777L;

    /**
     * Creates a new Map with specified dimensions.
     *
     * @param rows    number of rows in the tile map.
     * @param columns number of columns in the tile map.
     */
    Map(int rows, int columns) {
        this.tileLocations = new Tile[columns][rows];
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Set a specified Tile.
     *
     * @param tile the new tile.
     */
    void setTile(Tile tile) {
        tileLocations[tile.getX()][tile.getY()] = tile;
    }

    /**
     * Return the number of rows in the map.
     *
     * @return number of rows.
     */
    int getRows() {
        return rows;
    }

    /**
     * Return the number of columns in the map.
     *
     * @return number of columns.
     */
    int getColumns() {
        return columns;
    }

    /**
     * Return the tile at the specified x, y coordinate.
     *
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     * @return tile.
     */
    Tile getTile(int x, int y) {
        return tileLocations[x][y];
    }

    /**
     * Return all of the DoorTiles.
     *
     * @return all of the DoorTiles.
     */
    ArrayList<DoorTile> getDoor() {
        return doors;
    }

    /**
     * Set the DoorTiles of the game.
     *
     * @param doors DoorTiles of the game.
     */
    void setDoor(ArrayList<DoorTile> doors) {
        this.doors = doors;
    }

    /**
     * Return the all of the Tiles of the game.
     *
     * @return tiles of the game.
     */
    Tile[][] getTiles() {
        return tileLocations;
    }
}