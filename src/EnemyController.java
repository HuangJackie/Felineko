import processing.core.PApplet;

public class EnemyController extends EntityController {
    /**
     * Creates a new EntityController to manage drawing and updating location.
     *
     * @param felineko the PApplet of the main sketch
     */
    EnemyController(PApplet felineko) {
        super(felineko);
    }

    void updateLocation(Map map, Enemy enemy){
        int x = enemy.getX();
        int y = enemy.getY();
        String direction = enemy.getDirection();
        boolean leftEdge = map.getTile(x/30, (y+enemy.getHeight())/30).getType().equals("AIR") && direction.equals(Entity.LEFT);
        boolean rightEdge = map.getTile((x+enemy.getWidth())/30, (y+enemy.getHeight())/30).getType().equals("AIR") && direction.equals(Entity.RIGHT);
        boolean leftWall = map.getTile((x-1)/30, y/30).getType().equals("GROUND") && direction.equals(Entity.LEFT);
        boolean rightWall = map.getTile((x+enemy.getWidth()+1)/30, y/30).getType().equals("GROUND") && direction.equals(Entity.RIGHT);

        System.out.println(enemy.getX());
        if (direction.equals(Entity.LEFT)){
            moveLeft(map, enemy);
        } else {
            moveRight(map, enemy);
        }
        System.out.println(enemy.getVelocity() + " a");


        if (leftEdge || rightEdge || leftWall || rightWall){
            enemy.changeDirection();
        }
    }
}
