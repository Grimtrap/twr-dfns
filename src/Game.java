import javax.swing.*;
import java.util.LinkedList;

/**
 * Game.java
 * This is the class that handles the game and its operations and stuff
 * @author Eric Ke, Kyle To
 * Created: 2019/1/16 (because that was when i actually realized i needed this)
 * Last Updated: January 19 2019
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
    private boolean selling = false;
    private Tower sellSelected = null;

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

    public void spendGold(int amount){this.gold -= amount;}

    public double getGold(){
        return this.gold;
    }

    public double getLivesLeft(){
        return this.livesLeft;
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

    public TowerMenu getTowerMenu() {
        return towerMenu;
    }

    public void setTowerMenu(TowerMenu towerMenu) {
        this.towerMenu = towerMenu;
    }

    public boolean isSelling() {
        return selling;
    }

    public void setSelling(boolean selling) {
        this.selling = selling;
    }

    public Tower getSellSelected() {
        return sellSelected;
    }

    public void setSellSelected(Tower sellSelected) {
        this.sellSelected = sellSelected;
    }

    public LinkedList<Tower> getTowers() {
        return towers;
    }

    public void setTowers(LinkedList<Tower> towers) {
        this.towers = towers;
    }

    public EnemySpawner getSpawner() {
        return spawner;
    }

    public void setSpawner(EnemySpawner spawner) {
        this.spawner = spawner;
    }
}
