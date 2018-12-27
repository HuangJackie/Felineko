import processing.core.PApplet;

class PlayerController extends EntityController{

    PlayerController (PApplet felineko){
        super(felineko);
    }

    boolean canJump(Map map, Player hero){
        return (hero.getJumpCounter() < 2 && !onGround(map, hero) && !hero.isSlidOff()) || (onGround(map, hero) && !hero.hasJumpedOnce());
    }
}
