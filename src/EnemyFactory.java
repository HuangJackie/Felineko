import java.util.ArrayList;

/**
 * Create an EnemyFactory class that initializes all of the Enemies of the game.
 */
class EnemyFactory {
    /**
     * List of locations of the Enemies.
     */
    private final ArrayList<int[]> coordinates = new ArrayList<>();

    /**
     * Create an EnemyFactory class with specified Enemy locations.
     */
    EnemyFactory() {
        coordinates.add(new int[]{550, 1020});
        coordinates.add(new int[]{820, 1020});
        coordinates.add(new int[]{1100, 1170});
        coordinates.add(new int[]{1110, 990});
    }

    /**
     * Return the list of Enemies in the game.
     *
     * @return list of Enemies in the game.
     */
    ArrayList<Enemy> instantiateEnemies() {
        ArrayList<Enemy> activeEnemies = new ArrayList<>();
        for (int[] coordinate : coordinates) {
            int x = coordinate[0];
            int y = coordinate[1];
            Enemy enemy = new Enemy(x, y, 1, 7, 60, 60, 1, "ENEMY", 20);
            activeEnemies.add(enemy);
        }
        return activeEnemies;
    }
}
