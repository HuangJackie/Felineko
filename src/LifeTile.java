class LifeTile extends Tile {
    private int recoverPoints;
    private boolean active;

    /**
     * Creates a Tile with a type "name" and a X/Y location.
     *
     * @param type Type of tile/Name of the sprite png.
     * @param x    X-coordinate of the tile.
     * @param y    Y-coordinate of the tile.
     */
    LifeTile(String type, int x, int y) {
        super(type, x, y);
        recoverPoints = 40;
        active = true;
    }

    /**
     * Increases the health of the player.
     *
     * @param hero The player object to increase HP from.
     */
    void recoverHP(Player hero) {
        if (active) {
            if (hero.getHP()+recoverPoints >= 100){
                hero.setHP(100);
            }else {
                hero.setHP(hero.getHP() + recoverPoints);
            }
            active = false;
            System.out.println(this.getType() + " " + this.getX() + " " + this.getY());
            this.setType("AIR");
            setChanged();
            notifyObservers();
        }
    }

}
