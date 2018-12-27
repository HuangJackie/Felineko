/**
 * A TileFactory Class that creates various types of tiles.
 */
class TileFactory {
//    private static final String AIR = "-8927257";
//    private static final String GROUND = "-14112227";
//    private static final String SPIKE = "-14178020";
    private static final String AIR = "-8927257";
    private static final String GROUND = "-14178020";
    private static final String SPIKE = "-196600";
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
            default:
                return new Tile(type, x, y);
        }
    }
}