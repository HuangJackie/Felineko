class CoinTile extends Tile {

    private static int id = 0;
    private boolean collected = false;

    /**
     * Creates a Tile with a type "name" and a X/Y location.
     *
     * @param type Type of tile/Name of the sprite png.
     * @param x    X-coordinate of the tile.
     * @param y    Y-coordinate of the tile.
     */
    CoinTile(String type, int x, int y, boolean collidable) {
        super(type, x, y, collidable);
        CoinTile.id++;
    }

    void collect(Player hero){
        collected = true;
        this.setType("AIR");
        hero.collectCoin();
        setChanged();
        notifyObservers();
    }
}
