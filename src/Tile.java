import processing.core.PVector;

import java.io.Serializable;
import java.util.Observable;

/**
 * Creates a Tile object that invokes special properties/actions on the Player object.
 */
class Tile extends Observable implements Serializable {

    /**
     * The location of the Tile object.
     */
    private PVector position;

    /**
     * The name of the sprite of the Tile.
     */
    private String type;

    /**
     * Whether the Tile is collidable.
     */
    private boolean collidable;

    static final long serialVersionUID = 6469620024832591618L;

    /**
     * Creates a Tile with a type "name", a X/Y location, and whether it's collidable.
     *
     * @param type       type of tile/Name of the sprite png.
     * @param x          x-coordinate of the tile.
     * @param y          y-coordinate of the tile.
     * @param collidable whether the tile is collidable.
     */
    Tile(String type, int x, int y, boolean collidable) {
        position = new PVector(x, y);
        this.type = type;
        this.collidable = collidable;
    }

    /**
     * Changes the type "name" of the tile object to the desired name.
     *
     */
    void setTypeToAir() {
        type = TileFactory.AIR;
    }

    /**
     * Returns the X-coordinate of the tile.
     */
    int getX() {
        return (int) position.x;
    }

    /**
     * Returns the Y-coordinate of the tile.
     */
    int getY() {
        return (int) position.y;
    }

    /**
     * Returns the type "name" of the tile.
     */
    String getType() {
        return type;
    }

    /**
     * Return whether this Tile is Collidable.
     *
     * @return whether this Tile is Collidable.
     */
    boolean isCollidable() {
        return collidable;
    }

    /**
     * Set this Tile uncollidable.
     */
    void setCollidable() {
        this.collidable = false;
    }
}