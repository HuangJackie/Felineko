public class Enemy extends Entity {
    private int damagePoints;

    Enemy(int x, int y, int fallSpeed, int jumpSpeed, int damagePoints, int height, int width) {
        super(x, y, fallSpeed, jumpSpeed, height, width);
        this.damagePoints = damagePoints;
    }

    int getDamagePoints() {
        return damagePoints;
    }

    void changeDirection(){
        if (this.getDirection().equals(Entity.RIGHT)){
            this.setDirection(Entity.LEFT);
        }else{
            this.setDirection(Entity.RIGHT);
        }
    }

    boolean withinAttackRange(Player hero){
        int xHeroLeft = hero.getX();
        int xHeroRight = hero.getX() + hero.getWidth();
        int yHeroBottom = hero.getY() + hero.getHeight() - 1;
        boolean leftCollide = (getX() < xHeroLeft && xHeroLeft < getX() + getWidth()) && (getY() < yHeroBottom && yHeroBottom < getY() + getHeight());
        boolean rightCollide = (getX() < xHeroRight && xHeroRight < getX() + getWidth()) && (getY() < yHeroBottom && yHeroBottom < getY() + getHeight());
        return leftCollide || rightCollide;
    }
}
