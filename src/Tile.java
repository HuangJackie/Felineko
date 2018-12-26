import processing.core.PApplet;
import processing.core.PVector;

/**
 * Creates a Tile object that invokes special properties/actions on the Player object.
 */
public class Tile extends PApplet {
    private PVector position;
    //private int x;
    //private int y;
    private String type;

    /**
     * Creates a Tile with a type "name" and a X/Y location.
     *
     * @param type Type of tile/Name of the sprite png.
     * @param x X-coordinate of the tile.
     * @param y Y-coordinate of the tile.
     */
    public Tile(String type, int x, int y) {
        //this.x = x;
        //this.y = y;
        position = new PVector(x, y);
        this.type = type;
    }

    /**
     * Changes the type "name" of the tile object to the desired name.
     *
     * @param name New name of the tile.
     */
    public void setType(String name) {
        type = name;
    }

    /**
     * Returns the X-coordinate of the tile.
     */
    public int getX() {
        return (int) position.x;
    }

    /**
     * Returns the Y-coordinate of the tile.
     */
    public int getY() {
        return (int) position.y;
    }

    /**
     * Returns the type "name" of the tile.
     */
    public String getType() {
        return type;
    }
}