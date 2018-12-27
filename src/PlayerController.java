import processing.core.PApplet;

class PlayerController extends EntityController{

    PlayerController (PApplet felineko){
        super(felineko);
    }

    boolean canJump(Map map, Player hero){
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
}
