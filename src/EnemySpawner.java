import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class EnemySpawner {

    private int[] enemiesLeft;
    private int currentDifficulty;
    private Enemy[] currentEnemies;
    private Map map;

    private Random random = new Random();


    public EnemySpawner(String mapName) {
        map = new Map(mapName);
    }

    public void generateWave(int difficulty) {
        //amt of waves is determined by difficulty which is determined by the current wave
        enemiesLeft = new int[difficulty/100+1];
        Arrays.fill(enemiesLeft, 10);
        currentEnemies = new Enemy[difficulty/100 +1];
        generateEnemies();
    }

    public boolean canSpawnMore() {
        for(int e: enemiesLeft) {
            if(e > 0) {
                return true;
            }
        }

        return false;
    }

    public Enemy getNextEnemy(int currentIndex) {
        if(enemiesLeft[currentIndex] > 0) {
            enemiesLeft[currentIndex] -=1;
            Enemy next = currentEnemies[currentIndex].clone();
            next.setCoords(map.getStart()[0],map.getStart()[1]);
            return next;
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
            Attributes a = genAttributes();
            double[] healthSpeed = genHealthAndSpeed();
            LinkedList<Pathing> paths = map.getPathings();
            this.currentEnemies[i] = new Enemy(healthSpeed[0], healthSpeed[1], 100+currentDifficulty/20, a, paths);
        }

    }

    private Attributes genAttributes() {
        Attributes a = new Attributes();
        if(currentDifficulty > 100) {
            int[] numOfAttributes = new int[currentDifficulty / 100];
            for (int i : numOfAttributes) {
                i = random.nextInt(4);
                if (i == 0) {
                    a.setSlowResist(random.nextInt(500)/10.0);
                } if (i == 1) {
                    a.setBurnResist(random.nextInt(500)/10.0);
                } if (i == 2) {
                    if(random.nextInt(4) == 0) {
                        a.setFlying(true);
                    }
                } if (i == 3) {
                    a.setShielding(random.nextInt(currentDifficulty+100)/2.0);
                } if (i == 4) {
                    a.setRegen((currentDifficulty+100)/100.0);
                }
            }
        }
        return a;
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
