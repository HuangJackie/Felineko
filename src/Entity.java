import processing.core.PVector;

import java.io.Serializable;

/**
 * A Player or Enemy Entity in the game.
 */
abstract class Entity implements Serializable {
    /**
     * Coordinates of the Entity.
     */
    private PVector location;

    /**
     * Falling speed of the Entity.
     */
    private float fallSpeed;

    /**
     * Initial fall speed of the Entity.
     */
    private final float FALL_SPEED;

    /**
     * Maximum fall speed of the Entity.
     */
    private final int MAX_FALL_SPEED;

    /**
     * Jumping speed of the Entity.
     */
    private float jumpSpeed;

    /**
     * Initial Jumping speed of the Entity.
     */
    private final float JUMP_SPEED;

    /**
     * Horizontal moving speed of the Entity.
     */
    private float speed;

    /**
     * Maximum moving speed of the Entity.
     */
    private final float MOVE_SPEED;

    /**
     * Current horizontal direction the Entity is moving in.
     */
    private String direction;

    /**
     * Amount of health points.
     */
    private int HP;

    /**
     * Whether the Entity has just been damaged and is in a state of immunity.
     */
    private boolean damageState;

    /**
     * The last time the Entity was damaged.
     */
    private int damageTime;

    /**
     * Part of the name of sprites facing right.
     */
    static final String RIGHT = "RIGHT";

    /**
     * Part of the name of sprites facing left.
     */
    static final String LEFT = "LEFT";

    /**
     * Number of pixels in the the height of the sprite.
     */
    private final int HEIGHT;

    /**
     * Number of pixels in the width of the sprite.
     */
    private final int WIDTH;

    /**
     * Amount of damage the Entity can inflict on others.
     */
    private int damagePoints;

    /**
     * Name of the Sprite.
     */
    private final String NAME;

    static final long serialVersionUID = 6469620024877791618L;


    /**
     * Creates a new Entity with specified coordinates, size, horizontal and vertical speed and damagePoints.
     *
     * @param x            horizontal coordinate.
     * @param y            vertical coordinate.
     * @param fallSpeed    max falling speed.
     * @param jumpSpeed    max jumping speed.
     * @param height       number of pixels in the height of the sprite.
     * @param width        number of pixels in the width of the sprite.
     * @param maxVelocity  max horizontal speed.
     * @param name         name of the sprite file.
     * @param damagePoints amount of damage the player can cause.
     */
    Entity(int x, int y, int fallSpeed, int jumpSpeed, int height, int width, int maxVelocity, String name, int damagePoints) {
        location = new PVector(x, y);
        this.fallSpeed = fallSpeed;
        this.FALL_SPEED = fallSpeed;
        this.MAX_FALL_SPEED = 10;
        this.jumpSpeed = jumpSpeed;
        this.JUMP_SPEED = jumpSpeed;
        this.MOVE_SPEED = maxVelocity;
        this.speed = 1;
        direction = RIGHT;
        HP = 100;
        HEIGHT = height;
        WIDTH = width;
        this.damagePoints = damagePoints;
        NAME = name;
    }

    /**
     * Reset the jumpSpeed to its max.
     */
    void resetJumpSpeed() {
        jumpSpeed = JUMP_SPEED;
    }

    /**
     * Return the current jumpSpeed.
     *
     * @return current jumpSpeed.
     */
    int getJumpSpeed() {
        return (int) jumpSpeed;
    }

    /**
     * Decrease the jumpSpeed.
     */
    void decreJumpSpeed() {
        if (jumpSpeed > 0) {
            jumpSpeed -= 0.5;
        }
    }

    /**
     * Set the direction the Entity is moving in.
     *
     * @param newDirection direction.
     */
    void setDirection(String newDirection) {
        direction = newDirection;
    }

    /**
     * Return the direction the Entity is moving in.
     *
     * @return direction the Entity is moving in.
     */
    String getDirection() {
        return direction;
    }

    /**
     * Increment the horizontal speed.
     */
    void increVelocity() {
        if (speed <= MOVE_SPEED) {
            speed += 0.5;
        }
    }

    /**
     * Decrease the horizontal speed
     */
    void decreVelocity() {
        if (speed > 0) {
            speed -= 0.5;
        }
    }

    /**
     * Return the horizontal speed.
     *
     * @return horizontal speed.
     */
    int getSpeed() {
        return (int) speed;
    }

    /**
     * Return the falling speed.
     *
     * @return falling speed.
     */
    int getFallSpeed() {
        return (int) fallSpeed;
    }

    /**
     * Increment the falling speed.
     */
    void increFallSpeed() {
        if (fallSpeed <= MAX_FALL_SPEED) {
            fallSpeed += 0.3;
        }
    }

    /**
     * Reset the falling speed back to its minimum speed.
     */
    void resetFallSpeed() {
        fallSpeed = FALL_SPEED;
    }

    /**
     * Return the horizontal coordinate of the Entity.
     *
     * @return horizontal coordinate of the Entity.
     */
    int getX() {
        return (int) location.x;
    }

    /**
     * Return the vertical coordinate of the Entity.
     *
     * @return vertical coordinate of the Entity.
     */
    int getY() {
        return (int) location.y;
    }

    /**
     * Increment the horizontal coordinate by x.
     *
     * @param x amount to increment the horizontal coordinate by.
     */
    void increXBy(int x) {
        location.x += x;
    }

    /**
     * Increment the vertical coordinate by y.
     *
     * @param y amount to increment the veritical coordinate by.
     */
    void increYBy(int y) {
        location.y += y;
    }

    /**
     * Return the amount of HP of the Entity.
     *
     * @return amount of HP of the Entity.
     */
    int getHP() {
        return HP;
    }

    /**
     * Set the amount of HP of the Entity.
     *
     * @param HP amount of HP of the Entity.
     */
    void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Return of the Entity is not damaged.
     *
     * @return if the Entity is not damaged.
     */
    boolean isNotDamaged() {
        return !damageState;
    }

    /**
     * Set if the Entity is in a damaged state.
     *
     * @param damageState if the Entity is in a damaged state.
     */
    void setDamageState(boolean damageState) {
        this.damageState = damageState;
    }

    /**
     * Return the last time the Entity was damaged.
     *
     * @return last time the Entity was damaged.
     */
    int getDamageTime() {
        return damageTime;
    }

    /**
     * Set the last time the Entity was damaged.
     *
     * @param damageTime last time the Entity was damaged.
     */
    void setDamageTime(int damageTime) {
        this.damageTime = damageTime;
    }

    /**
     * Return the number of pixels in the height of the sprite.
     *
     * @return number of pixels in the height of the sprite.
     */
    int getHeight() {
        return HEIGHT;
    }

    /**
     * Return the number of pixels in the width of the sprite.
     *
     * @return number of pixels in the width of the sprite.
     */
    int getWidth() {
        return WIDTH;
    }

    /**
     * Return the name of the sprite.
     *
     * @return name of the sprite.
     */
    String getName() {
        return NAME;
    }

    /**
     * Returnt the amount of damage the Entity is able to inflict.
     *
     * @return amount of damage the Entity is able to inflict.
     */
    int getDamagePoints() {
        return damagePoints;
    }
}