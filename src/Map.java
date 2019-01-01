import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates a Map of Tile Objects to be displayed on the gamescreen.
 */
class Map implements Serializable {
    private Tile[][] tileLocations;
    private int rows;
    private int columns;
    private ArrayList<DoorTile> doors = new ArrayList<>();
    static final long serialVersionUID = 6469620024832591777L;

    /**
     * Creates a new Map with specified dimensions.
     *
     * @param rows number of rows in the tile map.
     * @param columns number of columns in the tile map.
     */
    Map(int rows, int columns) {
        System.out.println(rows + " " + columns);
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
    int getRows(){
        return rows;
    }

    /**
     * Return the number of columns in the map.
     *
     * @return number of columns.
     */
    int getColumns(){
        return columns;
    }

    /**
     * Return the tile at the specified x, y coordinate.
     *
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     * @return tile.
     */
    Tile getTile(int x, int y){
        return tileLocations[x][y];
    }

    ArrayList<DoorTile> getDoor() {
        return doors;
    }

    void setDoor(ArrayList<DoorTile> doors) {
        this.doors = doors;
    }
}