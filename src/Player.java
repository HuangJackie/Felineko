/**
 * Creates a Player object that the user can control.
 */
public class Player extends Entity {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 60;

    /**
     * Creates a Player with a specified HP, AP, X/Y location and a type (Knight or Mage).
     *
     * @param health HP of the Player.
     * @param attack AP/attack strength of the Player.
     * @param x X-coordinate of the Player.
     * @param y Y-coordinate of the Player.
     * @param type The type of player (Knight or Mage subclass).
     */
    public Player(int health, int attack, int x, int y, String type) {
        super(x, y, 1, 10);
    }
}