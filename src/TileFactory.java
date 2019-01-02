/**
 * A TileFactory Class that creates various types of tiles.
 */
class TileFactory {

    /**
     * The colour of the pixel on the TileMap corresponding to the Tile it specifies.
     */
    private static final String AIR_COLOUR = "-8927257";
    private static final String GROUND_COLOUR = "-14178020";
    private static final String SPIKE_COLOUR = "-196600";
    private static final String LIFE_COLOUR = "-46419";
    private static final String FISHCOIN_COLOUR = "-3091968";
    private static final String BORDER_COLOUR = "-16777216";
    private static final String DOOR_COLOUR = "-5367613";
    private static final String EXIT_COLOUR = "-1";

    /**
     * Name of the specified Tiles.
     */
    static final String AIR = "AIR";
    private static final String GROUND = "GROUND";
    static final String SPIKE = "SPIKE";
    static final String LIFE = "LIFE";
    static final String FISHCOIN = "COIN";
    private static final String BORDER = "BORDER";
    static final String DOOR = "DOOR";
    static final String EXIT = "EXIT";

    /**
     * Creates various subclasses of the class Tile.
     *
     * @param type the type of Tile.
     * @param x    horizontal coordinate.
     * @param y    vertical coordinate.
     * @return new Tile.
     */
    Tile createTile(String type, int x, int y) {
        switch (type) {
            case AIR_COLOUR:
                return new Tile(AIR, x, y, false);
            case GROUND_COLOUR:
                return new Tile(GROUND, x, y, true);
            case SPIKE_COLOUR:
                return new SpikeTile(SPIKE, x, y, false);
            case LIFE_COLOUR:
                return new LifeTile(LIFE, x, y, false);
            case FISHCOIN_COLOUR:
                return new CoinTile(FISHCOIN, x, y, false);
            case BORDER_COLOUR:
                return new Tile(BORDER, x, y, true);
            case DOOR_COLOUR:
                return new DoorTile(DOOR, x, y, true);
            case EXIT_COLOUR:
                return new Tile(EXIT, x, y, false);
            default:
                return new Tile(type, x, y, false);
        }
    }
}