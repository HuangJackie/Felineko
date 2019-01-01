class Enemy extends Entity {

    private boolean active;

    Enemy(int x, int y, int fallSpeed, int jumpSpeed, int damagePoints, int height, int width, int maxVelocity, String name) {
        super(x, y, fallSpeed, jumpSpeed, height, width, maxVelocity, name, damagePoints);
        active = true;
    }


    void changeDirection() {
        if (this.getDirection().equals(Entity.RIGHT)) {
            this.setDirection(Entity.LEFT);
        } else {
            this.setDirection(Entity.RIGHT);
        }
    }

    boolean withinAttackRange(Player hero) {
        int xHeroLeft = hero.getX();
        int xHeroRight = hero.getX() + hero.getWidth();
        int yHeroBottom = hero.getY() + hero.getHeight() - 1;
        boolean leftCollide = (getX() < xHeroLeft && xHeroLeft < getX() + getWidth()) && (getY() < yHeroBottom && yHeroBottom < getY() + getHeight());
        boolean rightCollide = (getX() < xHeroRight && xHeroRight < getX() + getWidth()) && (getY() < yHeroBottom && yHeroBottom < getY() + getHeight());
        return leftCollide || rightCollide;
    }

    boolean withinAttackedRangeRight(Player hero) {
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
        yHeroTop = hero.getY() - 20;

        xEnemyLeft = getX();
        yEnemyBottom = getY() + getHeight();
        xEnemyRight = getX() + getWidth();
        yEnemyTop = getY();

        boolean leftTopCollide = (xHeroLeft < xEnemyLeft && xEnemyLeft < xHeroRight) && (yHeroTop < yEnemyTop && yEnemyTop < yHeroBottom);
        boolean leftBottomCollide = (xHeroLeft < xEnemyLeft && xEnemyLeft < xHeroRight) && (yHeroTop < yEnemyBottom && yEnemyBottom < yHeroBottom);
        boolean rightTopCollide = (xHeroLeft < xEnemyRight && xEnemyRight < xHeroRight) && (yHeroTop < yEnemyTop && yEnemyTop < yHeroBottom);
        boolean rightBottomCollide = (xHeroLeft < xEnemyLeft && xEnemyLeft < xHeroRight) && (yHeroTop < yEnemyBottom && yEnemyBottom < yHeroBottom);
        return leftTopCollide || leftBottomCollide || rightTopCollide || rightBottomCollide;
    }

    void setInactive() {
        active = false;
    }

    boolean isActive() {
        return active;
    }
}
