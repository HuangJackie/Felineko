import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

class PlayerController extends EntityController {

    private int attackFrame;
    private PApplet felineko;
    private PImage[] attackSpriteLeft = new PImage[17];
    private PImage[] attackSpriteRight = new PImage[17];
    private int skipFrame;
    private PImage spriteLeft;
    private PImage spriteRight;
    private Player hero;

    PlayerController(PApplet felineko, Player hero) {
        super(felineko);
        this.hero = hero;
        this.felineko = felineko;
        attackFrame = 0;
        skipFrame = 0;
    }

    @Override
    void setUpSprite() {
        spriteRight = felineko.loadImage(hero.getName() + Entity.RIGHT + ".png");
        spriteLeft = felineko.loadImage(hero.getName() + Entity.LEFT + ".png");
        for (int i = 0; i < attackSpriteRight.length; i++) {
            //TODO make ATTACK static final variable.
            attackSpriteRight[i] = felineko.loadImage("HERO" + "ATTACK" + Entity.RIGHT + Integer.toString(i + 1) + ".png");
        }
        for (int i = 0; i < attackSpriteLeft.length; i++) {
            //TODO make ATTACK static final variable.
            attackSpriteLeft[i] = felineko.loadImage("HERO" + "ATTACK" + Entity.LEFT + Integer.toString(i + 1) + ".png");
        }
    }

    @Override
    void drawEntity() {
        if (hero.getDirection().equals(Entity.LEFT)) {
            felineko.image(spriteLeft, hero.getX(), hero.getY());
        } else {
            felineko.image(spriteRight, hero.getX(), hero.getY());
        }
    }

    private boolean canJump(Map map) {
        return (hero.getJumpCounter() < 2 && !onGround(map, hero) && !hero.isSlidOff()) || (onGround(map, hero) && !hero.hasJumpedOnce());
    }

    void notJumping(Map map) {
        if (onGround(map, hero)) {
            hero.setJumpCounter(0);
            hero.resetFallSpeed();
            hero.resetJumpSpeed();
        }
        hero.setHasJumpedOnce(false);
        hero.setSlidOff(false);
    }

    void playerJump(Map map) {
        if (canJump(map)) {
            jump(map, hero);
        }
        hero.setHasJumpedOnce(true);
        if (hero.hasJumpedOnce() && onGround(map, hero)) {
            hero.setSlidOff(true);
        }
    }

    void checkSpecialCollision(Map map) {
        for (int i = 0; i < hero.getHeight(); i += 29) {
            applyTileEffect(map, map.getTile(hero.getX() / 30, (hero.getY() + i) / 30));
            applyTileEffect(map, map.getTile((hero.getX() + hero.getWidth() - 1) / 30, (hero.getY() + i) / 30));
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
                    for (DoorTile doorTile : door) {
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

    boolean startedAttack() {
        return attackFrame != 0;
    }

    void drawAttack() {
        if (hero.getDirection().equals(Entity.LEFT)) {
            felineko.image(attackSpriteLeft[attackFrame], hero.getX() - 45, hero.getY());
            if (skipFrame == 0) {
                attackFrame++;
            }

            if (attackFrame >= attackSpriteLeft.length) {
                attackFrame = 0;
            }
        } else {
            felineko.image(attackSpriteRight[attackFrame], hero.getX(), hero.getY());
            if (skipFrame == 0) {
                attackFrame++;
            }

            if (attackFrame >= attackSpriteRight.length) {
                attackFrame = 0;
            }
        }
        skipFrame++;
        if (skipFrame == 2) {
            skipFrame = 0;
        }
    }

    void initializeAttackFrame() {
        attackFrame = 1;
    }


}
