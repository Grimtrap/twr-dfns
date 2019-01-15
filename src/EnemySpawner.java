import java.io.IOException;
import java.util.Random;

public class EnemySpawner {

    private int[] enemiesLeft;
    private int currentDifficulty;
    private Enemy[] currentEnemies;
    private Map map;

    private Random random = new Random();


    public EnemySpawner(String mapName) throws IOException {
        map = new Map("lol");
    }

    public void generateWave(int difficulty) {
        //amt of waves is determined by difficulty which is determined by the current wave
        enemiesLeft = new int[difficulty/100 +1];
        currentEnemies = new Enemy[difficulty/100 +1];
    }

    public Enemy getNextEnemy(int currentIndex) {
        if(enemiesLeft[currentIndex] > 0) {
            enemiesLeft[currentIndex] -=1;
            return currentEnemies[currentIndex];
        } else if(enemiesLeft.length < currentIndex+1){
            return getNextEnemy(currentIndex+1); //recursively traverses the array till all enemies are spawned i guess
        } else {
            return null;
        }
    }

    public void setDifficulty(int amt) {
        currentDifficulty = amt;
    }

    private void generateEnemies() {

        for(int i =0; i < currentEnemies.length; i++) {
            Attributes a = new Attributes();
            double[] healthSpeed = genHealthAndSpeed();
            this.currentEnemies[i] = new Enemy("resources/bad.png", healthSpeed[0], healthSpeed[1], 100+currentDifficulty/20, a, map.getPathings());
        }

    }

    private Attributes genAttributes() {
        return null; //BETTER FIX THIS LATER LOL
    }

    private double[] genHealthAndSpeed() {
        double multiplier = genMultiplier();
        double[] healthAndSpeed = new double[2]; //0 for health, 1 for speed
        healthAndSpeed[0] = (100+currentDifficulty)*multiplier;
        healthAndSpeed[1] = 3*(1+(1-multiplier));
        return healthAndSpeed;
    }

    private double genMultiplier() {
        return (random.nextInt(50) + 80)/100.0;
    }

    public int getEnemiesLeft(int i) {
        return enemiesLeft[i];
    }

}
