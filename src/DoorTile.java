/**
 * Creates a DoorTile object that blocks the Player object from passing until they have collected all CoinTiles.
 */
class DoorTile extends Tile {

    /**
     * Creates a DoorTile with a type "name", a X/Y location, and whether it's collidable.
     *
     * @param type       type of tile/Name of the sprite png.
     * @param x          x-coordinate of the tile.
     * @param y          y-coordinate of the tile.
     * @param collidable whether the tile is collidable.
     */
    DoorTile(String type, int x, int y, boolean collidable) {
        super(type, x, y, collidable);
    }


    /**
     * Sets the tile to invisible "AIR" when the Player collects 5 coins.
     */
    void remove() {
        setTypeToAir();
        setCollidable();
        setChanged();
        notifyObservers();
    }
}
