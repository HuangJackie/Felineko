import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

class EnemyController extends EntityController {

    private ArrayList<Enemy> activeEnemies;
    private ArrayList<PImage> sprites;

    /**
     * Creates a new EntityController to manage drawing and updating location.
     *
     * @param felineko the PApplet of the main sketch
     */
    EnemyController(PApplet felineko, Enemy enemy, ArrayList<Enemy> activeEnemies) {
        super(felineko, enemy);
        this.activeEnemies = activeEnemies;
        sprites = new ArrayList<>();
        for (Enemy activeEnemy : activeEnemies) {
            sprites.add(felineko.loadImage(activeEnemy.getName() + ".png"));
        }
    }

    void drawEnemies(){
        for (int i = 0; i < activeEnemies.size(); i++){
            getPApplet().image(sprites.get(i), activeEnemies.get(i).getX(), activeEnemies.get(i).getY());
        }
    }

    void updateLocation(Map map){
        for (Enemy activeEnemy : activeEnemies) {

            int x = activeEnemy.getX();
            int y = activeEnemy.getY();
            String direction = activeEnemy.getDirection();
            boolean leftEdge = map.getTile(x / 30, (y + activeEnemy.getHeight()) / 30).getType().equals("AIR") && direction.equals(Entity.LEFT);
            boolean rightEdge = map.getTile((x + activeEnemy.getWidth()) / 30, (y + activeEnemy.getHeight()) / 30).getType().equals("AIR") && direction.equals(Entity.RIGHT);
            boolean leftWall = map.getTile((x - 1) / 30, y / 30).getType().equals("GROUND") && direction.equals(Entity.LEFT);
            boolean rightWall = map.getTile((x + activeEnemy.getWidth() + 1) / 30, y / 30).getType().equals("GROUND") && direction.equals(Entity.RIGHT);

            if (direction.equals(Entity.LEFT)) {
                moveLeft(map, activeEnemy);
            } else {
                moveRight(map, activeEnemy);
            }

            if (leftEdge || rightEdge || leftWall || rightWall) {
                activeEnemy.changeDirection();
            }
        }
    }

    void attackPlayer(Player hero){
        int i = 0;
        while(activeEnemies.size() != 0 && i < activeEnemies.size()){
            if(activeEnemies.get(i).withinAttackRange(hero)){
                if (!hero.isDamageState()) {
                    if (hero.getHP() - activeEnemies.get(i).getDamagePoints() < 0){
                        hero.setHP(0);
                    } else {
                        hero.setHP(hero.getHP() - activeEnemies.get(i).getDamagePoints());
                    }
                    hero.setDamageTime(getPApplet().millis());
                    hero.setDamageState(true);
                }
            }
            i++;
        }
    }

    void attackEnemy(Player hero){
        int i = 0;
        while(activeEnemies.size() != 0 && i < activeEnemies.size()){
            if(activeEnemies.get(i).withinAttackedRangeRight(hero)){
                Enemy currentEnemy = activeEnemies.get(i);
                if (!currentEnemy.isDamageState()) {
                    currentEnemy.setHP(currentEnemy.getHP() - hero.getDamagePoints());
                    currentEnemy.setDamageTime(getPApplet().millis());
                    currentEnemy.setDamageState(true);
                    if (currentEnemy.getHP() <= 0){
                        currentEnemy.setInactive();
                    }
                }
            }
            i++;
        }
        updateInactive();
        System.out.println(activeEnemies.size());
    }

    private void updateInactive(){
        ArrayList<Integer> inactive = new ArrayList<>();
        for (int i = 0; i < activeEnemies.size(); i++){
            if (!activeEnemies.get(i).isActive()){
                inactive.add(i);
            }
        }

        for (Integer anInactive : inactive) {
            activeEnemies.remove((int)anInactive);
            sprites.remove((int)anInactive);
        }
    }

    void immunityUpdateAll(){
        for (Enemy enemy : activeEnemies){
            immunityUpdate(enemy);
        }
    }
}
