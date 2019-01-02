/**
 * An Enemy Entity in the game.
 */
class Enemy extends Entity {

    /**
     * Whether this Enemy is alive.
     */
    private boolean active;

    /**
     * Creates a new Enemy with specified coordinates, size, horizontal and vertical velocity and damagePoints.
     *
     * @param x            horizontal coordinate.
     * @param y            vertical coordinate.
     * @param fallSpeed    max falling velocity.
     * @param jumpSpeed    max jumping velocity.
     * @param height       number of pixels in the height of the sprite.
     * @param width        number of pixels in the width of the sprite.
     * @param maxVelocity  max horizontal velocity.
     * @param name         name of the sprite file.
     * @param damagePoints amount of damage the Enemy can cause.
     */
    Enemy(int x, int y, int fallSpeed, int jumpSpeed, int height, int width, int maxVelocity, String name, int damagePoints) {
        super(x, y, fallSpeed, jumpSpeed, height, width, maxVelocity, name, damagePoints);
        active = true;
    }

    /**
     * Change the direction the Enemy is moving in.
     */
    void changeDirection() {
        if (this.getDirection().equals(Entity.RIGHT)) {
            this.setDirection(Entity.LEFT);
        } else {
            this.setDirection(Entity.RIGHT);
        }
    }

    /**
     * Return whether attack was within attacking range.
     *
     * @param hero player to attack
     * @return whether attack was within attack range.
     */
    boolean withinAttackRange(Player hero) {
        int xHeroLeft = hero.getX();
        int xHeroRight = hero.getX() + hero.getWidth();
        int yHeroBottom = hero.getY() + hero.getHeight() - 1;
        boolean leftCollide = (getX() < xHeroLeft && xHeroLeft < getX() + getWidth()) && (getY() < yHeroBottom && yHeroBottom < getY() + getHeight());
        boolean rightCollide = (getX() < xHeroRight && xHeroRight < getX() + getWidth()) && (getY() < yHeroBottom && yHeroBottom < getY() + getHeight());
        return leftCollide || rightCollide;
    }

    /**
     * Return whether Enemy is within range to be attacked by a Player.
     *
     * @param hero player attempting to attack.
     * @return whether Enemy is within range to be attacked by a Player.
     */
    boolean withinAttackedRange(Player hero) {
        int xHeroLeft;
        int xHeroRight;
        int yHeroBottom;
        int yHeroTop;

        int xEnemyLeft;
        int yEnemyBottom;
        int xEnemyRight;
        int yEnemyTop;

        if (hero.getDirection().equals(Entity.RIGHT)) {
            xHeroLeft = hero.getX() + hero.getWidth();
            xHeroRight = hero.getX() + hero.getWidth() + 45;
        } else {
            xHeroLeft = hero.getX() + hero.getWidth() - 75;
            xHeroRight = hero.getX() + hero.getWidth() + 45 - 75;
        }
        yHeroBottom = hero.getY() + hero.getHeight() - 10;
        yHeroTop = hero.getY() + 10;

        xEnemyLeft = getX();
        yEnemyBottom = getY() + getHeight();
        xEnemyRight = getX() + getWidth();
        yEnemyTop = getY() + 20;

        boolean leftTopCollide = (xHeroLeft < xEnemyLeft && xEnemyLeft < xHeroRight) && (yHeroTop < yEnemyTop && yEnemyTop < yHeroBottom);
        boolean leftBottomCollide = (xHeroLeft < xEnemyLeft && xEnemyLeft < xHeroRight) && (yHeroTop < yEnemyBottom && yEnemyBottom < yHeroBottom);
        boolean rightTopCollide = (xHeroLeft < xEnemyRight && xEnemyRight < xHeroRight) && (yHeroTop < yEnemyTop && yEnemyTop < yHeroBottom);
        boolean rightBottomCollide = (xHeroLeft < xEnemyLeft && xEnemyLeft < xHeroRight) && (yHeroTop < yEnemyBottom && yEnemyBottom < yHeroBottom);
        return leftTopCollide || leftBottomCollide || rightTopCollide || rightBottomCollide;
    }

    /**
     * Set Enemy as inactive/defeated.
     */
    void setInactive() {
        active = false;
    }

    /**
     * Return if the Enemy is active/undefeated.
     *
     * @return if the Enemy is still active.
     */
    boolean isActive() {
        return active;
    }
}
