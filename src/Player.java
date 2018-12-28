/**
 * Creates a Player object that the user can control.
 */
class Player extends Entity {
    private int jumpCounter;
    private boolean hasJumpedOnce;
    private boolean slidOff;

    /**
     * Creates a Player with a specified HP, AP, X/Y location and a type (Knight or Mage).
     *
     * @param health HP of the Player.
     * @param attack AP/attack strength of the Player.
     * @param x X-coordinate of the Player.
     * @param y Y-coordinate of the Player.
     * @param type The type of player (Knight or Mage subclass).
     */
    Player(int health, int attack, int x, int y, String type, int height, int width) {
        super(x, y, 1, 7, height, width);
        jumpCounter = 0;
    }

    void setJumpCounter(int jumpCounter){
        this.jumpCounter = jumpCounter;
    }

    int getJumpCounter(){
        return jumpCounter;
    }

    boolean hasJumpedOnce() {
        return hasJumpedOnce;
    }

    void setHasJumpedOnce(boolean hasJumpedOnce) {
        this.hasJumpedOnce = hasJumpedOnce;
    }

    boolean isSlidOff() {
        return slidOff;
    }

    void setSlidOff(boolean slidOff) {
        this.slidOff = slidOff;
    }
}