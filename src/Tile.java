import processing.core.PVector;

/**
 * Creates a Tile object that invokes special properties/actions on the Player object.
 */
class Tile{
    private PVector position;
    private String type;

    /**
     * Creates a Tile with a type "name" and a X/Y location.
     *
     * @param type Type of tile/Name of the sprite png.
     * @param x X-coordinate of the tile.
     * @param y Y-coordinate of the tile.
     */
    Tile(String type, int x, int y) {
        position = new PVector(x, y);
        this.type = type;
    }

    /**
     * Changes the type "name" of the tile object to the desired name.
     *
     * @param name New name of the tile.
     */
    void setType(String name) {
        type = name;
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
}