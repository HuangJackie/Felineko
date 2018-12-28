import processing.core.PVector;

/**
 * A Player or Enemy Entity in the game.
 */
abstract class Entity {
    private PVector location;
    private float fallSpeed;
    private final float FALLSPEED;
    private final int MAXFALLSPEED;
    private float jumpSpeed;
    private final float JUMPSPEED;
    private float velocity;
    private final float VELOCITY;
    private String direction;
    private int HP;
    private boolean damageState;
    private int damageTime;
    static final String RIGHT = "RIGHT";
    static final String LEFT = "LEFT";
    private final int HEIGHT;
    private final int WIDTH;

    Entity(int x, int y, int fallSpeed, int jumpSpeed, int height, int width){
        location = new PVector(x, y);
        this.fallSpeed = fallSpeed;
        this.FALLSPEED = fallSpeed;
        this.MAXFALLSPEED = 10;
        this.jumpSpeed = jumpSpeed;
        this.JUMPSPEED = jumpSpeed;
        this.VELOCITY = 5;
        this.velocity = 1;
        direction = RIGHT;
        HP = 100;
        HEIGHT = height;
        WIDTH = width;
    }

    void resetJumpSpeed(){
        jumpSpeed = JUMPSPEED;
    }

    int getJumpSpeed(){
        return (int) jumpSpeed;
    }

    void decreJumpSpeed(){
        if (jumpSpeed > 0){
            jumpSpeed -= 0.5;
        }
    }

    void setDirection(String newDirection){
        direction = newDirection;
    }

    String getDirection(){
        return direction;
    }

    void increVelocity(){
        if (velocity <= VELOCITY){
            velocity += 0.5;
        }
    }

    void decreVelocity(){
        if (velocity > 0){
            velocity -= 0.5;
        }
    }

    int getVelocity(){
        return (int) velocity;
    }

    int getFallSpeed(){
        return (int) fallSpeed;
    }

    void increFallSpeed(){
        if (fallSpeed <= MAXFALLSPEED){
            fallSpeed += 0.3;
        }
    }

    void resetFallSpeed(){
        fallSpeed = FALLSPEED;
    }

    int getX(){
        return (int) location.x;
    }

    int getY(){
        return (int) location.y;
    }

    void increXBy(int x){
        location.x += x;
    }

    void increYBy(int y){
        location.y += y;
    }

    int getHP() {
        return HP;
    }

    void setHP(int HP) {
        this.HP = HP;
    }

    boolean isDamageState() {
        return damageState;
    }

    void setDamageState(boolean damageState) {
        this.damageState = damageState;
    }

    int getDamageTime() {
        return damageTime;
    }

    void setDamageTime(int damageTime) {
        this.damageTime = damageTime;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }
}