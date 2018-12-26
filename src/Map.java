/**
 * Creates a Map of Tile Objects to be displayed on the gamescreen.
 */
public class Map {
    private Tile[][] tileLocations;
    private int rows;
    private int columns;

    /**
     * Creates a new Map with specified dimensions.
     *
     * @param rows number of rows in the tile map.
     * @param columns number of columns in the tile map.
     */
    Map(int rows, int columns) {
        this.tileLocations = new Tile[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Set the TileLocations in the Tile Map.
     *
     * @param tileLocations the tile locations.
     */
    public void setTileLocations(Tile[][] tileLocations) {
        this.tileLocations = tileLocations;
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
}