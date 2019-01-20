import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * EnemySpawner.java
 * generates and spawns enemies on the map
 * @author Eric Ke
 * Date: 2019/1/7
 */
public class EnemySpawner {

    private int[] enemiesLeft;
    private int currentDifficulty;
    private Enemy[] currentEnemies;
    private Map map;

    private Random random = new Random();


    /**
     * creates a new enemy generator
     * @param mapName name of the map
     */
    public EnemySpawner(String mapName) {

        map = new Map(mapName);
        enemiesLeft = new int[]{0};
    }

    /**
     * generates a wave of enemies
     * @param difficulty the difficulty of the wave
     */
    public void generateWave(int difficulty) {
        currentDifficulty = difficulty;
        enemiesLeft = new int[difficulty/100+1];
        Arrays.fill(enemiesLeft, 10);
        currentEnemies = new Enemy[difficulty/100 +1];
        generateEnemies();
    }

    /**
     * checks whether there are enemies left
     * @return if there are enemies left in the wave
     */
    public boolean canSpawnMore() {
        for(int e: enemiesLeft) {
            if(e > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * gets the next enemy to spawn
     * @return an enemy to spawn
     */
    public Enemy getNextEnemy() {
        return findNextEnemy(0);
    }


    private Enemy findNextEnemy(int currentIndex) {
        //a method which finds the next suitable enemy to spawn
        if(enemiesLeft[currentIndex] > 0) {
            enemiesLeft[currentIndex] -=1;
            Enemy next = currentEnemies[currentIndex].clone();
            next.setCoords(map.getStart()[0],map.getStart()[1]);
            return next;
        } else if(enemiesLeft.length < currentIndex+1){
            return findNextEnemy(currentIndex+1); //recursively traverses the array till all enemies are spawned i guess
        } else {
            return null;
        }
    }

    /**
     * gives what enemies are to spawn in the next wave
     * @return an array with the enemies to spawn
     */
    public Enemy[] getNextWavesEnemies() {
        return currentEnemies;
    }

    private void generateEnemies() {
        //calls several methods to generate enemies to spawn in a wave
        for(int i =0; i < currentEnemies.length; i++) {
            Attributes a = genAttributes();
            double[] healthSpeed = genHealthAndSpeed();
            LinkedList<Pathing> paths = map.getPathings();
            this.currentEnemies[i] = new Enemy(determineImage(a, healthSpeed[0]), healthSpeed[0], healthSpeed[1], 100+currentDifficulty/20, a, paths);
        }

    }

    private String determineImage(Attributes a, double health) {
        String name = "";
        double multiplier = health/(100+currentDifficulty);
        if(multiplier >= 0.6 && multiplier < 0.8) {
            name+="Tiny";
        } else if(multiplier >= 0.8 && multiplier <= 1.0) {
            name+="Small";
        } else if(multiplier >= 1.0 && multiplier <= 1.2) {
            name+="Medium";
        } else {
            name+="Large";
        }

        if(a.isFlying()) {
            name += "Air";
        } else {
            name += "Ground";
        }

        name+="Enemy";

        return name;
    }

    private Attributes genAttributes() {
        Attributes a = new Attributes();
        //generates attributes and amount based on difficulty
        if(currentDifficulty > 100) {
            int[] numOfAttributes = new int[currentDifficulty / 100];
            if(random.nextBoolean()) { //only has a small chance to actually give attributes
                for (int i : numOfAttributes) {
                    //yes, there is a chance that it'll select one that's already been done and redo it
                    //it's a feature to make multiple traits slightly rarer
                    if (random.nextBoolean())
                        i = random.nextInt(4);
                    if (i == 0) {
                        a.setSlowResist(random.nextInt(500) / 10.0);
                    }
                    else if (i == 1) {
                        a.setBurnResist(random.nextInt(500) / 10.0);
                    }
                    else if (i == 2) {
                        if (random.nextInt(4) == 0) {
                            a.setFlying(true);
                        }
                    }
                    else if (i == 3) {
                        a.setShielding(random.nextInt(currentDifficulty + 100) / 2.0);
                    }
                    else if (i == 4) {
                        a.setRegen((currentDifficulty + 100) / 100.0);
                    }
                }
            }

            //once again is only chance of having damage resistances or weaknesses
            if(random.nextBoolean()) {
                double[] resists = {random.nextInt(20)-10, random.nextInt(20)-10, random.nextInt(20)-10};
                a.setDmgResists(resists);
            }

        }
        return a;
    }

    private double[] genHealthAndSpeed() {
        double multiplier = genMultiplier();
        double[] healthAndSpeed = new double[2]; //0 for health, 1 for speed
        healthAndSpeed[0] = (100+currentDifficulty)*multiplier;
        healthAndSpeed[1] = 2*(1+(1-multiplier));
        return healthAndSpeed;
    }

    private double genMultiplier() {
        return (random.nextInt(40) + 70)/100.0;
    }

    public int getEnemiesLeft(int i) {
        return enemiesLeft[i];
    }

}
