import processing.core.PApplet;

import java.util.Observable;

/**
 * An EntityController that manages the displaying/drawing of the Entity.
 */
abstract class EntityController extends Observable {
    /**
     * The current PApplet of the game.
     */
    private PApplet felineko;

    /**
     * Creates a new EntityController to manage drawing and updating location.
     *
     * @param felineko the PApplet of the main sketch
     */
    EntityController(PApplet felineko) {
        this.felineko = felineko;
    }

    /**
     * Load the image of the sprite of the Entity.
     */
    abstract void setUpSprite();


    /**
     * Update if the Entity was still in a state of immunity.
     *
     * @param entity the Entity to update.
     */
    void immunityUpdate(Entity entity) {
        if (getPApplet().millis() - entity.getDamageTime() > 1000) {
            entity.setDamageState(false);
        }
    }

    /**
     * Displaying the entity on screen.
     */
    abstract void drawEntity();

    /**
     * Updating x, y coordinates of player when falling due to gravity.
     *
     * @param map    the current tile map.
     * @param entity the entity.
     */
    void applyGravity(Map map, Entity entity) {
        entity.increYBy(2 * entity.getFallSpeed());
        entity.increFallSpeed();

        for (int i = 0; i < entity.getWidth(); i++) {
            if (map.getTile((entity.getX() + i) / 30, (entity.getY() + entity.getHeight() - 1) / 30).isCollidable()) {
                entity.increYBy(-(entity.getY() + entity.getHeight()) % 30);
                entity.resetFallSpeed();
                entity.resetJumpSpeed();
            }
        }
    }

    /**
     * Return whether the Entity is on top of a collidable Tile.
     *
     * @param map    tileMap of the Game
     * @param entity Entity to check
     * @return whether the Entity is on top of a collidable Tile.
     */
    boolean onGround(Map map, Entity entity) {
        for (int i = 0; i < entity.getWidth(); i++) {
            if (map.getTile((entity.getX() + i) / 30, (entity.getY() + entity.getHeight()) / 30).isCollidable()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updating x, y coordinates of player when jumping.
     *
     * @param map    the current tile map.
     * @param entity the entity.
     */
    void jump(Map map, Entity entity) {
        entity.increYBy(-2 * entity.getJumpSpeed());
        entity.decreJumpSpeed();
        if (entity.getJumpSpeed() > 0) {
            entity.resetFallSpeed();
        }

        for (int i = 0; i < entity.getWidth(); i++) {
            if (map.getTile((entity.getX() + i) / 30, (entity.getY()) / 30).isCollidable()) {
                entity.increYBy(30 - (entity.getY() + entity.getHeight()) % 30);
            }
        }
    }

    /**
     * Updating x, y coordinates of player when moving left.
     *
     * @param map    the current tile map.
     * @param entity the entity.
     */
    void moveLeft(Map map, Entity entity) {
        entity.increXBy(-entity.getSpeed());
        entity.increVelocity();
        entity.setDirection(Entity.LEFT);
        collisionCorrect(map, entity);
    }

    /**
     * Updating x, y coordinates of player when moving right.
     *
     * @param map    the current tile map.
     * @param entity the entity.
     */
    void moveRight(Map map, Entity entity) {
        entity.increXBy(entity.getSpeed());
        entity.increVelocity();
        entity.setDirection(Entity.RIGHT);
        collisionCorrect(map, entity);
    }

    /**
     * Updating x, y coordinates of player when not moving.
     *
     * @param map    the current tile map.
     * @param entity the entity.
     */
    void notMoving(Map map, Entity entity) {
        if (entity.getDirection().equals(Entity.LEFT)) {
            entity.increXBy(-entity.getSpeed());
        } else {
            entity.increXBy(entity.getSpeed());
        }
        entity.decreVelocity();
        collisionCorrect(map, entity);
    }

    /**
     * Added collision detection and correction moving left and right.
     *
     * @param map    the current tile map.
     * @param entity the entity.
     */
    private void collisionCorrect(Map map, Entity entity) {
        if (entity.getDirection().equals(Entity.LEFT)) {
            entity.increXBy(-entity.getSpeed());
            for (int i = 0; i < entity.getHeight(); i++) {
                if (map.getTile((entity.getX()) / 30, (entity.getY() + i) / 30).isCollidable()) {
                    entity.increXBy(30 - (entity.getX()) % 30);
                }
            }
        } else {
            entity.increXBy(entity.getSpeed());
            for (int i = 0; i < entity.getHeight(); i++) {
                if (map.getTile((entity.getX() + entity.getWidth()) / 30, (entity.getY() + i) / 30).isCollidable()) {
                    entity.increXBy(-(entity.getX() + entity.getWidth()) % 30);
                }
            }
        }
    }

    /**
     * Return the PApplet of the Game.
     *
     * @return PApplet of the Game.
     */
    PApplet getPApplet() {
        return felineko;
    }
}