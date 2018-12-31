/**
 * A TileFactory Class that creates various types of tiles.
 */
class TileFactory {
    public static final String AIR = "-8927257";
    public static final String GROUND = "-14178020";
    public static final String SPIKE = "-196600";
    public static final String LIFE = "-46419";
    public static final String FISHCOIN = "-3091968";
    public static final String BORDER = "-16777216";
    public static final String DOOR = "-5367613";
    public static final String EXIT = "-1";

    /**
     * Creates various subclasses of the class Tile.
     *
     * @param type the type of Tile.
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     * @return new Tile.
     */
    Tile createTile(String type, int x, int y){
        switch(type) {
            case AIR:
                return new Tile("AIR", x, y, false);
            case GROUND:
                return new Tile("GROUND", x, y, true);
            case SPIKE:
                return new SpikeTile("SPIKE", x, y, false);
            case LIFE:
                return new LifeTile("LIFE", x, y, false);
            case FISHCOIN:
                return new CoinTile("COIN", x, y, false);
            case BORDER:
                return new Tile("BORDER", x, y, true);
            case DOOR:
                return new DoorTile("DOOR", x, y, true);
            case EXIT:
                return new Tile("EXIT", x, y, false);
            default:
                return new Tile(type, x, y, false);
        }
    }
}