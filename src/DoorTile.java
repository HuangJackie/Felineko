public class DoorTile extends Tile {
    /**
     * Creates a Tile with a type "name" and a X/Y location.
     *
     * @param type       Type of tile/Name of the sprite png.
     * @param x          X-coordinate of the tile.
     * @param y          Y-coordinate of the tile.
     * @param collidable
     */
    DoorTile(String type, int x, int y, boolean collidable) {
        super(type, x, y, collidable);
    }

    void remove(){
        setType("AIR");
        setCollidable(false);
        setChanged();
        notifyObservers();
    }
}
