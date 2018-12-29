import processing.core.PApplet;

import java.util.ArrayList;

class EnemyController extends EntityController {

    private ArrayList<Enemy> activeEnemies;

    /**
     * Creates a new EntityController to manage drawing and updating location.
     *
     * @param felineko the PApplet of the main sketch
     */
    EnemyController(PApplet felineko, ArrayList<Enemy> activeEnemies) {
        super(felineko);
        this.activeEnemies = activeEnemies;
    }

    void updateLocation(Map map, Enemy enemy){
        int x = enemy.getX();
        int y = enemy.getY();
        String direction = enemy.getDirection();
        boolean leftEdge = map.getTile(x/30, (y+enemy.getHeight())/30).getType().equals("AIR") && direction.equals(Entity.LEFT);
        boolean rightEdge = map.getTile((x+enemy.getWidth())/30, (y+enemy.getHeight())/30).getType().equals("AIR") && direction.equals(Entity.RIGHT);
        boolean leftWall = map.getTile((x-1)/30, y/30).getType().equals("GROUND") && direction.equals(Entity.LEFT);
        boolean rightWall = map.getTile((x+enemy.getWidth()+1)/30, y/30).getType().equals("GROUND") && direction.equals(Entity.RIGHT);

        if (direction.equals(Entity.LEFT)){
            moveLeft(map, enemy);
        } else {
            moveRight(map, enemy);
        }

        if (leftEdge || rightEdge || leftWall || rightWall){
            enemy.changeDirection();
        }
    }

    void attackPlayer(Player hero){
        int i = 0;
        System.out.println("on");

        while(activeEnemies.size() != 0 && i < activeEnemies.size()){
            if(activeEnemies.get(i).withinAttackRange(hero)){
                if (!hero.isDamageState()) {
                    hero.setHP(hero.getHP() - activeEnemies.get(i).getDamagePoints());
                    hero.setDamageTime(getPApplet().millis());
                    hero.setDamageState(true);
                }
            }
            i++;
        }
    }
}
