import processing.core.PVector;

/**
 * A Player or Enemy Entity in the game.
 */
public abstract class Entity {
    private PVector location;
    private float fallSpeed;
    private final float FALLSPEED;
    private final int MAXFALLSPEED;
    private float jumpSpeed;
    private final float JUMPSPEED;
    private float velocity;
    private final float VELOCITY;
    private String direction;
    static final String RIGHT = "RIGHT";
    static final String LEFT = "LEFT";

    public Entity(int x, int y, int fallSpeed, int jumpSpeed){
        location = new PVector(x, y);
        this.fallSpeed = fallSpeed;
        this.FALLSPEED = fallSpeed;
        this.MAXFALLSPEED = 10;
        this.jumpSpeed = jumpSpeed;
        this.JUMPSPEED = jumpSpeed;
        this.VELOCITY = 5;
        this.velocity = 1;
        direction = RIGHT;
    }

    public void resetJumpSpeed(){
        jumpSpeed = JUMPSPEED;
    }

    public int getJumpSpeed(){
        return (int) jumpSpeed;
    }

    public void decreJumpSpeed(){
        if (jumpSpeed > 0){
            jumpSpeed -= 0.5;
        }
    }

    public void setDirection(String newDirection){
        direction = newDirection;
    }

    public String getDirection(){
        return direction;
    }

    public void resetVelocity(){
        velocity = 1;
    }

    public void increVelocity(){
        if (velocity <= VELOCITY){
            velocity += 0.5;
        }
    }

    public void decreVelocity(){
        if (velocity > 0){
            velocity -= 0.5;
        }
    }

    public int getVelocity(){
        return (int) velocity;
    }

    public int getFallSpeed(){
        return (int) fallSpeed;
    }

    public void increFallSpeed(){
        if (fallSpeed <= MAXFALLSPEED){
            fallSpeed += 0.4;
        }
    }

    public void resetFallSpeed(){
        fallSpeed = FALLSPEED;
    }

    public int getX(){
        return (int) location.x;
    }

    public int getY(){
        return (int) location.y;
    }

    public void increXBy(int x){
        location.x += x;
    }

    public void increYBy(int y){
        location.y += y;
    }
}