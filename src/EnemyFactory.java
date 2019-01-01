import java.util.ArrayList;

class EnemyFactory {
    private final ArrayList<int[]> coordinates = new ArrayList<>();

    EnemyFactory(){
        coordinates.add(new int[] {550, 1050});
        coordinates.add(new int[] {820, 1050});
        coordinates.add(new int[] {1110, 1200});
        coordinates.add(new int[] {1110, 1020});
    }

    ArrayList<Enemy> instantiateEnemies(){
        ArrayList<Enemy> activeEnemies = new ArrayList<>();
        for (int[] coordinate : coordinates) {
            int x = coordinate[0];
            int y = coordinate[1];
            Enemy enemy = new Enemy(x, y, 1, 7, 20, 30, 60, 1, "SNAKE");
            activeEnemies.add(enemy);
        }
        return activeEnemies;
    }
}
