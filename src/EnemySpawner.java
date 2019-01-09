import java.io.IOException;

public class EnemySpawner {

    private int enemiesLeft;
    private int difficultyLeft;
    private int timeBeforeNextSpawn;
    private Enemy currentEnemy;

    public EnemySpawner(int difficulty) {
        this.difficultyLeft = difficulty;
    }

    public EnemySpawner() throws IOException {
        Map map = new Map("lol");
        this.difficultyLeft = 100;
        this.enemiesLeft = 10;
        Attributes a = new Attributes();
        enemiesLeft = 10;
        timeBeforeNextSpawn = 0;
        a.setBurnResist(0);
        a.setFlying(false);
        a.setRegen(0);
        a.setShielding(0);
        a.setSlowResist(5);
        this.currentEnemy = new Enemy("resources/bad.png", 100, 3, 3, a, map.getPathings());
    }

    public Enemy getNextEnemy() {
        enemiesLeft -= 1;
        return this.currentEnemy;
    }

    private void generateEnemy() {

    }
}
