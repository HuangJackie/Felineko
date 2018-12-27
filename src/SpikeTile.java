class SpikeTile extends Tile {
    private int damagePoints;

    /**
     * Creates a Tile with a type "name" and a X/Y location.
     *
     * @param type Type of tile/Name of the sprite png.
     * @param x    X-coordinate of the tile.
     * @param y    Y-coordinate of the tile.
     */
    SpikeTile(String type, int x, int y) {
        super(type, x, y);
        damagePoints = 10;
    }

    /**
     * Decreases the health of the player and initializes a period of immunity.
     *
     * @param hero The player object to decrease HP from.
     */
    void damageHP(Player hero) {
        hero.setHP(hero.getHP()-damagePoints);
        hero.setDamageState(true);
//        hero.setDamageTime();
    }
}
