public class TileFactory {
    public static final String AIR = " ";
    public static final String GROUND = "";

    public Tile createTile(String type, int x, int y){
        switch(type) {
            case AIR:
                return new Tile("AIR", x, y);
            case GROUND:
                return new Tile("GROUND", x, y);
            default:
                return new Tile(type, x, y);
        }
    }
}