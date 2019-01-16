import javax.swing.*;
import java.util.LinkedList;

public class Game {

    private Map map;
    private LinkedList<Enemy> enemies;
    private Clock clock;
    private EnemySpawner spawner;
    private ThreadTest t;

    public Game(String mapName) {
        map = new Map(mapName);
        enemies = new LinkedList<>();
        clock = new Clock();
        spawner = new EnemySpawner(mapName);
        spawner.generateWave(0);
        t = new ThreadTest(enemies, spawner);
        t.start();
        SwingUtilities.invokeLater(() -> {
            try {
                new TestLmao("lol", enemies);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void updateClock() {
        clock.update();
    }

    public void spawnEnemy() {
        enemies.add(spawner.getNextEnemy(0));
    }
}
