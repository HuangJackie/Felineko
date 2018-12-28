import processing.core.PApplet;
import processing.core.PImage;

/**
 * An EntityController that manages the displaying/drawing of the Entity.
 */
class EntityController {
    private PApplet felineko;

    /**
     * Creates a new EntityController to manage drawing and updating location.
     *
     * @param felineko the PApplet of the main sketch
     */
    EntityController (PApplet felineko){
        this.felineko = felineko;
    }

    /**
     * Displaying the entity on screen.
     *
     * @param name name of the entity sprite.
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     */
    void drawEntity(String name, int x, int y){
        PImage newSprite = felineko.loadImage(name + ".png");
        felineko.image(newSprite, x, y);
    }

    /**
     * Updating x, y coordinates of player when falling due to gravity.
     *
     * @param map the current tile map.
     * @param entity the entity.
     */
    void applyGravity(Map map, Entity entity){
        entity.increYBy(2*entity.getFallSpeed());
        entity.increFallSpeed();

        for (int i = 0; i<entity.getWidth(); i++) {
            if (map.getTile((entity.getX()+i)/30, (entity.getY()+entity.getHeight())/30).getType().equals("GROUND")) {
                entity.increYBy(-(entity.getY()+entity.getHeight())%30);
                entity.resetFallSpeed();
                entity.resetJumpSpeed();
            }
        }
    }

    boolean onGround(Map map, Entity entity){
        for (int i = 0; i<entity.getWidth(); i++) {
            if (map.getTile((entity.getX()+i)/30, (entity.getY()+entity.getHeight())/30).getType().equals("GROUND")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updating x, y coordinates of player when jumping.
     *
     * @param map the current tile map.
     * @param entity the entity.
     */
    void jump(Map map, Entity entity){
        entity.increYBy(-2*entity.getJumpSpeed());
        entity.decreJumpSpeed();
        if(entity.getJumpSpeed() > 0){
            entity.resetFallSpeed();
        }

        for (int i = 0; i<entity.getWidth(); i++){
            if (map.getTile((entity.getX()+i)/30, (entity.getY())/30).getType().equals("GROUND")) {
                entity.increYBy(30-(entity.getY()+entity.getHeight())%30);
            }
        }
    }

    /**
     * Updating x, y coordinates of player when moving left.
     *
     * @param map the current tile map.
     * @param entity the entity.
     */
    void moveLeft(Map map, Entity entity){
        entity.increXBy(-entity.getVelocity());
        entity.increVelocity();
        entity.setDirection(Entity.LEFT);
        collisionCorrect(map, entity);
    }

    /**
     * Updating x, y coordinates of player when moving right.
     *
     * @param map the current tile map.
     * @param entity the entity.
     */
    void moveRight(Map map, Entity entity){
        entity.increXBy(entity.getVelocity());
        entity.increVelocity();
        entity.setDirection(Entity.RIGHT);
        collisionCorrect(map, entity);
    }

    /**
     * Updating x, y coordinates of player when not moving.
     *
     * @param map the current tile map.
     * @param entity the entity.
     */
    void notMoving(Map map, Entity entity){
        if (entity.getDirection().equals(Entity.LEFT)){
            entity.increXBy(-entity.getVelocity());
        }else{
            entity.increXBy(entity.getVelocity());
        }
        entity.decreVelocity();
        collisionCorrect(map, entity);
    }

    /**
     * Added collision detection and correction moving left and right.
     *
     * @param map the current tile map.
     * @param entity the entity.
     */
    private void collisionCorrect(Map map, Entity entity){
        if (entity.getDirection().equals(Entity.LEFT)){
            entity.increXBy(-entity.getVelocity());
            for (int i = 0; i<entity.getHeight(); i++) {
                if (map.getTile((entity.getX())/30, (entity.getY()+i)/30).getType().equals("GROUND")) {
                    entity.increXBy(30-(entity.getX())%30);
                }
            }
        }else {
            entity.increXBy(entity.getVelocity());
            for (int i = 0; i < entity.getHeight(); i++) {
                if (map.getTile((entity.getX() + entity.getWidth()) / 30, (entity.getY() + i) / 30).getType().equals("GROUND")) {
                    entity.increXBy(-(entity.getX() + entity.getWidth()) % 30);
                }
            }
        }
    }
}