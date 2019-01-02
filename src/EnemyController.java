import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * Create a controller class to manage to logic of the Enemy Class
 */
class EnemyController extends EntityController {

    /**
     * List of active enemies.
     */
    private ArrayList<Enemy> activeEnemies;

    /**
     * List of sprites for the enemies.
     */
    private ArrayList<PImage> sprites;

    /**
     * Creates a new EntityController to manage drawing and updating location.
     *
     * @param felineko      the PApplet of the main sketch
     * @param activeEnemies the list of Enemies.
     */
    EnemyController(PApplet felineko, ArrayList<Enemy> activeEnemies) {
        super(felineko);
        this.activeEnemies = activeEnemies;
    }

    /**
     * Update the location of the Enemies.
     *
     * @param map tileMap of the Game.
     */
    void updateLocation(Map map) {
        for (Enemy activeEnemy : activeEnemies) {

            int x = activeEnemy.getX();
            int y = activeEnemy.getY();
            String direction = activeEnemy.getDirection();
            boolean leftEdge = map.getTile(x / 30, (y + activeEnemy.getHeight()) / 30).getType().equals("AIR") && direction.equals(Entity.LEFT);
            boolean rightEdge = map.getTile((x + activeEnemy.getWidth()) / 30, (y + activeEnemy.getHeight()) / 30).getType().equals("AIR") && direction.equals(Entity.RIGHT);
            boolean leftWall = map.getTile((x - 1) / 30, (y + activeEnemy.getHeight() - 1) / 30).isCollidable() && direction.equals(Entity.LEFT);
            boolean rightWall = map.getTile((x + activeEnemy.getWidth()) / 30, (y + activeEnemy.getHeight() - 1) / 30).isCollidable() && direction.equals(Entity.RIGHT);

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

    /**
     * Attack the Player object.
     *
     * @param hero Player object to attack.
     */
    void attackPlayer(Player hero) {
        int i = 0;
        while (activeEnemies.size() != 0 && i < activeEnemies.size()) {
            if (activeEnemies.get(i).withinAttackRange(hero)) {
                if (hero.isNotDamaged()) {
                    if (hero.getHP() - activeEnemies.get(i).getDamagePoints() < 0) {
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

    /**
     * Receiving attacks from the Player.
     *
     * @param hero the Player attacking.
     */
    void attackEnemy(Player hero) {
        int i = 0;
        while (activeEnemies.size() != 0 && i < activeEnemies.size()) {
            if (activeEnemies.get(i).withinAttackedRange(hero)) {
                Enemy currentEnemy = activeEnemies.get(i);
                if (currentEnemy.isNotDamaged()) {
                    currentEnemy.setHP(currentEnemy.getHP() - hero.getDamagePoints());
                    currentEnemy.setDamageTime(getPApplet().millis());
                    currentEnemy.setDamageState(true);
                    if (currentEnemy.getHP() <= 0) {
                        currentEnemy.setInactive();
                    }
                }
            }
            i++;
        }
        updateInactive();
    }

    /**
     * Remove Enemies that were defeated by the Player.
     */
    private void updateInactive() {
        ArrayList<Integer> inactive = new ArrayList<>();
        for (int i = 0; i < activeEnemies.size(); i++) {
            if (!activeEnemies.get(i).isActive()) {
                inactive.add(i);
            }
        }

        for (Integer anInactive : inactive) {
            activeEnemies.remove((int) anInactive);
            sprites.remove((int) anInactive);
        }
    }

    /**
     * Update if the Enemy was still in a period of immunity from the last attack.
     */
    void immunityUpdateAll() {
        for (Enemy enemy : activeEnemies) {
            immunityUpdate(enemy);
        }
    }

    @Override
    void setUpSprite() {
        sprites = new ArrayList<>();
        for (Enemy activeEnemy : activeEnemies) {
            sprites.add(getPApplet().loadImage(activeEnemy.getName() + ".png"));
        }
    }

    @Override
    void drawEntity() {
        for (int i = 0; i < activeEnemies.size(); i++) {
            getPApplet().image(sprites.get(i), activeEnemies.get(i).getX(), activeEnemies.get(i).getY());
        }
    }
}
