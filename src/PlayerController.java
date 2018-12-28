import processing.core.PApplet;

class PlayerController extends EntityController{
    private PApplet felineko;

    PlayerController (PApplet felineko){
        super(felineko);
        this.felineko = felineko;
    }

    private boolean canJump(Map map, Player hero){
        return (hero.getJumpCounter() < 2 && !onGround(map, hero) && !hero.isSlidOff()) || (onGround(map, hero) && !hero.hasJumpedOnce());
    }

    void notJumping(Map map, Player hero){
        if (onGround(map, hero)) {
            hero.setJumpCounter(0);
            hero.resetFallSpeed();
            hero.resetJumpSpeed();
        }
        hero.setHasJumpedOnce(false);
        hero.setSlidOff(false);
    }

    void playerJump(Map map, Player hero){
        if (canJump(map, hero)){
            jump(map, hero);
        }
        hero.setHasJumpedOnce(true);
        if (hero.hasJumpedOnce() && onGround(map, hero)){
            hero.setSlidOff(true);
        }
    }

    void checkSpecialCollision(Map map, Player hero) {
        for (int i = 0; i < Player.HEIGHT; i+=29){
            applyTileEffect(map, map.getTile(hero.getX()/30, (hero.getY()+i)/30), hero);
            applyTileEffect(map, map.getTile((hero.getX()+Player.WIDTH-1)/30, (hero.getY()+i)/30), hero);
        }
    }

    private void applyTileEffect(Map map, Tile tile, Player hero) {
        String type = tile.getType();
        if (type.equals("SPIKE")){
            if (!hero.isDamageState()) {
                ((SpikeTile) tile).damageHP(hero);
                hero.setDamageTime(felineko.millis());
            }
        }else if (type.equals("LIFE")){
            ((LifeTile) tile).recoverHP(hero);
//            map.setTile(new Tile("AIR", tile.getX(), tile.getY()));
        }
    }

    void immunityUpdate(Player hero){
        if (felineko.millis() - hero.getDamageTime() > 1000) {
            hero.setDamageState(false);
        }
    }

}
