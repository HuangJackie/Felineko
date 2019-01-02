/**
 * Creates a Player object that the user can control.
 */
class Player extends Entity {

    /**
     * The number of times the Player has jumped consecutively in the air.
     */
    private int jumpCounter;

    /**
     * Whether the Play has already jumped consecutively in the air.
     */
    private boolean hasJumpedOnce;

    /**
     * Whether the Player fell off of a collidable Tile without jumping.
     */
    private boolean slidOff;

    /**
     * Number of Fish Coins the Player has collected.
     */
    private int numCoin;
    static final long serialVersionUID = 2700457091028890106L;

    /**
     * Creates a new Player with specified coordinates, size and damagePoints.
     *
     * @param x            horizontal coordinate.
     * @param y            vertical coordinate.
     * @param height       number of pixels in the height of the sprite.
     * @param width        number of pixels in the width of the sprite.
     * @param maxVelocity  max horizontal velocity.
     * @param name         name of the sprite file.
     * @param damagePoints amount of damage the player can cause.
     */
    Player(int x, int y, int height, int width, int maxVelocity, String name, int damagePoints) {
        super(x, y, 1, 7, height, width, maxVelocity, name, damagePoints);
        jumpCounter = 0;
        numCoin = 0;
    }

    /**
     * Set the value of jumpCounter
     *
     * @param jumpCounter Number of times player has jumped in air.
     */
    void setJumpCounter(int jumpCounter) {
        this.jumpCounter = jumpCounter;
    }

    /**
     * Return the value of jumpCounter
     *
     * @return Number of times player has jumped in air.
     */
    int getJumpCounter() {
        return jumpCounter;
    }

    /**
     * Return if the player has jumped at least once.
     *
     * @return if the player has jumped at least once.
     */
    boolean hasJumpedOnce() {
        return hasJumpedOnce;
    }

    /**
     * Set if the player has jumped at least once.
     *
     * @param hasJumpedOnce if the player has jumped at least once.
     */
    void setHasJumpedOnce(boolean hasJumpedOnce) {
        this.hasJumpedOnce = hasJumpedOnce;
    }

    /**
     * Return if the player fell off the edge without jumping.
     *
     * @return if the player fell off the edge without jumping.
     */
    boolean isSlidOff() {
        return slidOff;
    }

    /**
     * Set if the player fell off the edge without jumping.
     *
     * @param slidOff if the player fell off the edge without jumping.
     */
    void setSlidOff(boolean slidOff) {
        this.slidOff = slidOff;
    }

    /**
     * Increment the number of coins collected.
     */
    void collectCoin() {
        numCoin++;
    }

    /**
     * Return the number of coins collected.
     *
     * @return the number of coins collected.
     */
    int getNumCoin() {
        return numCoin;
    }

    /**
     * If all the coins have been collected.
     *
     * @return if all of the coins have been collected.
     */
    boolean allCollected() {
        return numCoin == 5;
    }
}