import java.io.IOException;
import java.util.Random;

public class EnemySpawner {

    private int enemiesLeft;
    private int difficultyLeft;
    private int currentDifficulty;
    private Enemy currentEnemy;
    private Map map;

    public EnemySpawner(int difficulty) {
        this.difficultyLeft = difficulty;
    }

    public EnemySpawner(String mapName) throws IOException {
        map = new Map("lol");
        this.difficultyLeft = 100;
        this.enemiesLeft = 10;
        enemiesLeft = 10;
    }

    public Enemy getNextEnemy() {
        enemiesLeft -= 1;
        return (Enemy)currentEnemy.clone();
    }

    public void setDifficulty(int amt) {
        currentDifficulty = amt;
        difficultyLeft = amt;
    }

    private void generateEnemy() {
        Random random = new Random();

        Attributes a = new Attributes();
        if(currentDifficulty < 10) {
            a.setBurnResist(0);
            a.setFlying(false);
            a.setRegen(0);
            a.setShielding(0);
            a.setSlowResist(0);
            this.currentEnemy = new Enemy("resources/bad.png", 100, 3, 3, a, map.getPathings());
        }

    }
}
