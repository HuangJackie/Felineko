import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

class PlayerController extends EntityController{

    private int attackFrame;
    private PApplet felineko;
    private PImage[] attackSprite = new PImage[17];
    private int skipFrame;
    private PImage sprite;
    private Player hero;

    PlayerController (PApplet felineko, Player hero){
        super(felineko);
        this.hero = hero;
        this.felineko = felineko;
        attackFrame = 0;
        skipFrame = 0;
    }

    @Override
    void setUpSprite() {
        sprite = felineko.loadImage(hero.getName() + ".png");
        for (int i = 0; i < attackSprite.length; i++){
            //TODO make ATTACK static final variable.
            attackSprite[i] = felineko.loadImage("HERO" + "ATTACK" + Integer.toString(i+1)+ ".png");
        }
    }

    @Override
    void drawEntity(){
        felineko.image(sprite, hero.getX(), hero.getY());
    }

    private boolean canJump(Map map){
        return (hero.getJumpCounter() < 2 && !onGround(map, hero) && !hero.isSlidOff()) || (onGround(map, hero) && !hero.hasJumpedOnce());
    }

    void notJumping(Map map){
        if (onGround(map, hero)) {
            hero.setJumpCounter(0);
            hero.resetFallSpeed();
            hero.resetJumpSpeed();
        }
        hero.setHasJumpedOnce(false);
        hero.setSlidOff(false);
    }

    void playerJump(Map map){
        if (canJump(map)){
            jump(map, hero);
        }
        hero.setHasJumpedOnce(true);
        if (hero.hasJumpedOnce() && onGround(map, hero)){
            hero.setSlidOff(true);
        }
    }

    void checkSpecialCollision(Map map) {
        for (int i = 0; i < hero.getHeight(); i+=29){
            applyTileEffect(map, map.getTile(hero.getX()/30, (hero.getY()+i)/30));
            applyTileEffect(map, map.getTile((hero.getX()+hero.getWidth()-1)/30, (hero.getY()+i)/30));
        }
    }

    private void applyTileEffect(Map map, Tile tile) {
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
                if (hero.allCollected()) {
                    ArrayList<DoorTile> door = map.getDoor();
                    for (DoorTile doorTile : door){
                        doorTile.remove();
                    }
                }
                break;
            case "EXIT":
                setChanged();
                notifyObservers();
                break;
        }
    }

    boolean startedAttack(){
        return attackFrame != 0;
    }

    void drawAttack(){
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
