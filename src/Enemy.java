public class Enemy extends Entity {
    private int damagePoints;

    Enemy(int x, int y, int fallSpeed, int jumpSpeed, int damagePoints, int height, int width) {
        super(x, y, fallSpeed, jumpSpeed, height, width);
        this.damagePoints = damagePoints;
    }

    public int getDamagePoints() {
        return damagePoints;
    }

    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }

    void changeDirection(){
        if (this.getDirection().equals(Entity.RIGHT)){
            this.setDirection(Entity.LEFT);
        }else{
            this.setDirection(Entity.RIGHT);
        }
    }
}
