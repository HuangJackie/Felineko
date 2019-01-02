/**
 * Creates a CoinTile object that the Player object can collect.
 */
class CoinTile extends Tile {

    /**
     * Creates a CoinTile with a type "name", a X/Y location, and whether it's collidable.
     *
     * @param type       type of tile/Name of the sprite png.
     * @param x          x-coordinate of the tile.
     * @param y          y-coordinate of the tile.
     * @param collidable whether the tile is collidable.
     */
    CoinTile(String type, int x, int y, boolean collidable) {
        super(type, x, y, collidable);
    }

    /**
     * Sets the tile to invisible "AIR" when the player collects it.
     *
     * @param hero player collecting the CoinTile.
     */
    void collect(Player hero) {
        this.setTypeToAir();
        hero.collectCoin();
        setChanged();
        notifyObservers();
    }
}
