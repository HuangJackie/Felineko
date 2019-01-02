import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * A PlayerController that manages the displaying/drawing of the Player.
 */
class PlayerController extends EntityController {

    /**
     * The current attack animation frame shown.
     */
    private int attackFrame;

    /**
     * The current PApplet of the game.
     */
    private PApplet felineko;

    /**
     * The list of left facing attack sprite images.
     */
    private PImage[] attackSpriteLeft = new PImage[17];

    /**
     * The list of right facing attack sprite images.
     */
    private PImage[] attackSpriteRight = new PImage[17];

    /**
     * Speed of the attack animation.
     */
    private int skipFrame;

    /**
     * The sprite of the player facing left.
     */
    private PImage spriteLeft;

    /**
     * The sprite of the player facing right.
     */
    private PImage spriteRight;

    /**
     * The current Player.
     */
    private Player hero;

    /**
     * Creates a new PlayerController to manage drawing and updating location.
     *
     * @param felineko the PApplet of the main sketch
     */
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
            attackSpriteRight[i] = felineko.loadImage("HERO" + "ATTACK" + Entity.RIGHT + Integer.toString(i + 1) + ".png");
        }
        for (int i = 0; i < attackSpriteLeft.length; i++) {
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

    /**
     * Return whether the Player is allowed to jump again.
     *
     * @param map tileMap of the Game.
     * @return whether the Player is allowed to jump again.
     */
    private boolean canJump(Map map) {
        return (hero.getJumpCounter() < 2 && !onGround(map, hero) && !hero.isSlidOff()) || (onGround(map, hero) && !hero.hasJumpedOnce());
    }

    /**
     * Reset the velocity values of the Player if not jumping.
     *
     * @param map tileMap of the Game.
     */
    void notJumping(Map map) {
        if (onGround(map, hero)) {
            hero.setJumpCounter(0);
            hero.resetFallSpeed();
            hero.resetJumpSpeed();
        }
        hero.setHasJumpedOnce(false);
        hero.setSlidOff(false);
    }

    /**
     * Update the Player's coordinates to jump.
     *
     * @param map tileMap of the Game.
     */
    void playerJump(Map map) {
        if (canJump(map)) {
            jump(map, hero);
        }
        hero.setHasJumpedOnce(true);
        if (hero.hasJumpedOnce() && onGround(map, hero)) {
            hero.setSlidOff(true);
        }
    }

    /**
     * Check of the Player has collided with special Tiles.
     *
     * @param map tileMap of the Game.
     */
    void checkSpecialCollision(Map map) {
        for (int i = 0; i < hero.getHeight(); i += 29) {
            applyTileEffect(map, map.getTile(hero.getX() / 30, (hero.getY() + i) / 30));
            applyTileEffect(map, map.getTile((hero.getX() + hero.getWidth() - 1) / 30, (hero.getY() + i) / 30));
        }
    }

    /**
     * Apply the effect of the special tiles to the Player.
     *
     * @param map  tileMap of the Game.
     * @param tile special Tile.
     */
    private void applyTileEffect(Map map, Tile tile) {
        String type = tile.getType();
        switch (type) {
            case TileFactory.SPIKE:
                if (hero.isNotDamaged()) {
                    ((SpikeTile) tile).damageHP(hero);
                    hero.setDamageTime(getPApplet().millis());
                }
                break;
            case TileFactory.LIFE:
                ((LifeTile) tile).recoverHP(hero);
                break;
            case TileFactory.FISHCOIN:
                ((CoinTile) tile).collect(hero);
                if (hero.allCollected()) {
                    ArrayList<DoorTile> door = map.getDoor();
                    for (DoorTile doorTile : door) {
                        doorTile.remove();
                    }
                }
                break;
            case TileFactory.EXIT:
                setChanged();
                notifyObservers();
                break;
        }
    }

    /**
     * Check if the attack animation is still displaying.
     *
     * @return if the attack animation is still displaying.
     */
    boolean startedAttack() {
        return attackFrame != 0;
    }

    /**
     * Display the attack animation.
     */
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

    /**
     * Begin the attack animation.
     */
    void initializeAttackFrame() {
        attackFrame = 1;
    }


}
