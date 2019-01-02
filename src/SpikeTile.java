/**
 * Creates a SpikeTile object that damages the Player object.
 */
class SpikeTile extends Tile {

    /**
     * The amount of damage that will be inflicted to a Player if collided with.
     */
    private int damagePoints;

    /**
     * Creates a SpikeTile with a type "name", a X/Y location, and whether it's collidable.
     *
     * @param type       type of tile/Name of the sprite png.
     * @param x          x-coordinate of the tile.
     * @param y          y-coordinate of the tile.
     * @param collidable whether the tile is collidable.
     */
    SpikeTile(String type, int x, int y, boolean collidable) {
        super(type, x, y, collidable);
        damagePoints = 10;
    }

    /**
     * Decreases the health of the player and initializes a period of immunity.
     *
     * @param hero The player object to decrease HP from.
     */
    void damageHP(Player hero) {
        if (hero.getHP() - damagePoints < 0) {
            hero.setHP(0);
        } else {
            hero.setHP(hero.getHP() - damagePoints);
        }
        hero.setDamageState(true);
    }
}
