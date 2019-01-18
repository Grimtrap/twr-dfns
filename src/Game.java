import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Game.java
 * This is the class that handles the game and its operations and stuff
 * @author Eric Ke
 * Created: 2019/1/16
 */
public class Game {

    private Map map;
    private LinkedList<Enemy> enemies;
    private LinkedList<Tower> towers;
    private Clock clock;
    private EnemySpawner spawner;
    private UpdaterThread enemyThread;
    private TowersThread towerThread;
    private double gold;
    private double livesLeft;
    private TowerMenu towerMenu;

    /**
     * creates a new game
     * @param mapName name of the map
     */
    public Game(String mapName) {
        map = new Map(mapName);
        towerMenu = new TowerMenu();
        enemies = new LinkedList<>();
        towers = new LinkedList<>();
        towers.add(new RifleTower(100,100));
        clock = new Clock();
        spawner = new EnemySpawner(mapName);
        spawner.generateWave(0);
        enemyThread = new UpdaterThread(enemies, spawner, this);
        enemyThread.start();
        towerThread = new TowersThread(towers, enemies);
        towerThread.start();
        SwingUtilities.invokeLater(() -> new GameFrame(enemies, towers, map));
        gold = 400;
        livesLeft = 10;
    }

    public void updateClock() {
        clock.update();
    }

    public void spawnEnemy() {
        enemies.add(spawner.getNextEnemy(0));
    }

    public void loseLife() {
        this.livesLeft -= 1;
    }

    public void gainGold(int amount) {
        this.gold += amount;
    }
}
