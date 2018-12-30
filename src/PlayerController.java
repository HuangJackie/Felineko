import processing.core.PApplet;
import processing.core.PImage;

class PlayerController extends EntityController{

    private int attackFrame;
    private PApplet felineko;
    private PImage[] attackSprite = new PImage[17];
    private int skipFrame;

    PlayerController (PApplet felineko, Player hero){
        super(felineko, hero);
        this.felineko = felineko;
        attackFrame = 0;
        skipFrame = 0;
    }

    void loadAttackSprites(Player hero){
        for (int i = 0; i < attackSprite.length; i++){
            //TODO make ATTACK static final variable.
            attackSprite[i] = felineko.loadImage("HERO" + "ATTACK" + Integer.toString(i+1)+ ".png");
        }
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
        for (int i = 0; i < hero.getHeight(); i+=29){
            applyTileEffect(map.getTile(hero.getX()/30, (hero.getY()+i)/30), hero);
            applyTileEffect(map.getTile((hero.getX()+hero.getWidth()-1)/30, (hero.getY()+i)/30), hero);
        }
    }

    private void applyTileEffect(Tile tile, Player hero) {
        String type = tile.getType();
        switch (type) {
            case "SPIKE":
                if (!hero.isDamageState()) {
                    ((SpikeTile) tile).damageHP(hero);
                    hero.setDamageTime(getPApplet().millis());
                }
                break;
            case "LIFE":
                ((LifeTile) tile).recoverHP(hero);
                break;
            case "COIN":
                ((CoinTile) tile).collect(hero);
                break;
        }
    }

    boolean startedAttack(){
        return attackFrame != 0;
    }

    void drawAttack(Player hero){
        felineko.image(attackSprite[attackFrame], hero.getX(), hero.getY());
        if(skipFrame == 0){
            attackFrame++;
        }
        skipFrame++;
        if(skipFrame == 2){
            skipFrame = 0;
        }
        if (attackFrame >= attackSprite.length){
            attackFrame = 0;
        }
    }

    void initializeAttackFrame(){
        attackFrame = 1;
    }



}
