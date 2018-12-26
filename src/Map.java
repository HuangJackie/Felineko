/**
 * Creates a Map of Tile Objects to be displayed on the gamescreen.
 */
public class Map {
    private Tile[][] tileLocations;
    private int rows;
    private int columns;

    public Map(int rows, int columns) {
        this.tileLocations = new Tile[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public void setTileLocations(Tile[][] tileLocations) {
        this.tileLocations = tileLocations;
    }

    public void setTile(Tile tile) {
        tileLocations[tile.getX()][tile.getY()] = tile;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public Tile getTile(int x, int y){
        return tileLocations[x][y];
    }
}