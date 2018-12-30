/**
 * A TileFactory Class that creates various types of tiles.
 */
class TileFactory {
    public static final String AIR = "-8927257";
    public static final String GROUND = "-14178020";
    public static final String SPIKE = "-196600";
    public static final String LIFE = "-46419";
    public static final String FISHCOIN = "-3091968";

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
                return new Tile("AIR", x, y);
            case GROUND:
                return new Tile("GROUND", x, y);
            case SPIKE:
                return new SpikeTile("SPIKE", x, y);
            case LIFE:
                return new LifeTile("LIFE", x, y);
            case FISHCOIN:
                return new CoinTile("COIN", x, y);
            default:
                return new Tile("COIN", x, y);
        }
    }
}