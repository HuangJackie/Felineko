/**
 * Creates a LifeTile object that heals the Player object.
 */
class LifeTile extends Tile {

    /**
     * Number of health points the Player can recover when colliding with this LifeTile.
     */
    private int recoverPoints;

    /**
     * Whether the Tile has been used.
     */
    private boolean active;

    /**
     * Creates a LifeTile with a type "name", a X/Y location, and whether it's collidable.
     *
     * @param type       type of tile/Name of the sprite png.
     * @param x          x-coordinate of the tile.
     * @param y          y-coordinate of the tile.
     * @param collidable whether the tile is collidable.
     */
    LifeTile(String type, int x, int y, boolean collidable) {
        super(type, x, y, collidable);
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
            if (hero.getHP() + recoverPoints >= 100) {
                hero.setHP(100);
            } else {
                hero.setHP(hero.getHP() + recoverPoints);
            }
            active = false;
            this.setTypeToAir();
            setChanged();
            notifyObservers();
        }
    }

}
