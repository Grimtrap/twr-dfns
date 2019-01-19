import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

/**
 * Game.java
 * This is the class that handles the game and its operations and stuff
 * @author Eric Ke
 * Created: 2019/1/16 (because that was when i actually realized i needed this)
 */
public class Game {

    private Map map;
    private LinkedList<Enemy> enemies;
    private LinkedList<Tower> towers;
    private Clock clock;
    private EnemySpawner spawner;
    private UpdaterThread enemyThread;
    private TowersThread towerThread;
    private int wave;
    private boolean isSpawning;
    private double gold;
    private double livesLeft;
    private TowerMenu towerMenu;
    private Tower selected = null;

    /**
     * creates a new game
     * @param mapName name of the map
     */
    public Game(String mapName) {
        map = new Map(mapName);
        towerMenu = new TowerMenu(this);
        enemies = new LinkedList<>();
        towers = new LinkedList<>();
        clock = new Clock();
        spawner = new EnemySpawner(mapName);
        spawner.generateWave(0);
        wave = 0;
        enemyThread = new UpdaterThread(enemies, spawner, this);
        enemyThread.start();
        towerThread = new TowersThread(towers, enemies);
        towerThread.start();
        SwingUtilities.invokeLater(() -> new GameFrame(enemies, towers, map, this));
        gold = 400;
        livesLeft = 10;
    }

    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    public void loseLife() {
        this.livesLeft -= 1;
    }

    public void gainGold(int amount) {
        this.gold += amount;
    }

    public double getGold(){
        return this.gold;
    }

    public Tower getSelected() {
        return selected;
    }

    public void setSelected(Tower selected) {
        this.selected = selected;
    }

    public TowersThread getTowerThread() {
        return towerThread;
    }

    public void setTowerThread(TowersThread towerThread) {
        this.towerThread = towerThread;
    }
}
