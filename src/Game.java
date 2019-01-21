import javax.swing.*;
import java.util.LinkedList;

/**
 * Game.java
 * This is the class that handles the game and its operations and stuff
 * @author Eric Ke, Kyle To, Michael T.
 * Created: 2019/1/16 (because that was when i actually realized i needed this)
 * Last Updated: January 19 2019
 */
public class Game {

    private Map map;
    private LinkedList<Enemy> enemies;
    private LinkedList<Tower> towers;
    private Clock clock;
    private EnemySpawner spawner;
    private EnemyThread enemyThread;
    private TowersThread towerThread;

    private int wave;
    private double gold;
    private double livesLeft;
    private TowerMenu towerMenu;
    private Tower selected = null;
    private boolean selling = false;
    private Tower sellSelected = null;
    private TowerStats towerStats;

    /**
     * creates a new game
     * @param mapName name of the map
     */
    public Game(String mapName) {
        map = new Map(mapName);
        towerStats = new TowerStats();
        towerMenu = new TowerMenu(this);
        enemies = new LinkedList<>();
        towers = new LinkedList<>();
        clock = new Clock();
        spawner = new EnemySpawner(mapName);
        wave = 0;
        enemyThread = new EnemyThread(enemies, spawner, this);
        enemyThread.start();
        towerThread = new TowersThread(towers);
        towerThread.start();
        SwingUtilities.invokeLater(() -> new GameFrame(enemies, towers, map, this));
        gold = 400;
        livesLeft = 10;
    }

    /**
     * gets a list of the enemies in the game
     * @return LinkedList of enemies in the game
     */
    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * loses a life
     */
    public void loseLife() {
        this.livesLeft -= 1;
    }

    /**
     * gives user gold
     * @param amount amount gained
     */
    public void gainGold(int amount) {
        this.gold += amount;
    }

    /**
     * subtracts gold when spending
     * @param amount amount spent
     */
    public void spendGold(int amount){this.gold -= amount;}

    /**
     * gets amount of gold the user has
     * @return amount of gold
     */
    public double getGold(){
        return this.gold;
    }

    /**
     * gets number of lives left
     * @return lives left
     */
    public double getLivesLeft(){
        return this.livesLeft;
    }

    /**
     * gets currently selected tower
     * @return the selected tower
     */
    public Tower getSelected() {
        return selected;
    }

    /**
     * sets the currently selected tower
     * @param selected tower to be selected
     */
    public void setSelected(Tower selected) {
        this.selected = selected;
    }

    /**
     * gets the thread which manages towers
     * @return the tower thread
     */
    public TowersThread getTowerThread() {
        return towerThread;
    }

    /**
     * sets the towers thread
     * @param towerThread the thread to set it to
     */
    public void setTowerThread(TowersThread towerThread) {
        this.towerThread = towerThread;
    }

    /**
     * gets the menu for purchasing towers
     * @return the tower menu
     */
    public TowerMenu getTowerMenu() {
        return towerMenu;
    }


    /**
     * gets whether or not the user is selling towers
     * @return if the user is currently selling
     */
    public boolean isSelling() {
        return selling;
    }

    /**
     * sets if the user is in selling mode
     * @param selling whether the user is selling towers
     */
    public void setSelling(boolean selling) {
        this.selling = selling;
    }


    /**
     * sets the tower to be sold
     * @param sellSelected the tower to be sold
     */
    public void setSellSelected(Tower sellSelected) {
        this.sellSelected = sellSelected;
    }

    /**
     * gets a list of the towers in the game
     * @return a list containing the towers in the game
     */
    public LinkedList<Tower> getTowers() {
        return towers;
    }

    /**
     * sets the list of towers in the game
     * @param towers the list to be the towers in the game
     */
    public void setTowers(LinkedList<Tower> towers) {
        this.towers = towers;
    }

    /**
     * gets the enemy spawner
     * @return the spawner
     */
    public EnemySpawner getSpawner() {
        return spawner;
    }

    /**
     * gets the current wave number
     * @return the wave the game is currently on
     */
    public int getWave() {
        return wave;
    }

}
