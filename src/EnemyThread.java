import java.util.LinkedList;

/**
 * EnemyThread.java
 *
 * @author Eric Ke
 */
public class EnemyThread extends Thread {
    private LinkedList<Enemy> enemies;
    public boolean running;
    private Clock clock;
    private EnemySpawner spawner;
    private double duration;
    private Game game;
    private int num;

    public EnemyThread(LinkedList<Enemy> enemies, EnemySpawner spawner, Game game) {
        this.enemies = enemies;
        this.clock = new Clock();
        this.running = true;
        this.spawner = spawner;
        duration = 0.5;
        this.game = game;
    }

    public synchronized void run() {
        while(running) {
            clock.update();
            duration -= clock.getElapsedTime();

            if(duration <= 0) {
                duration = 0.5;
                if(spawner.canSpawnMore()) {
                    enemies.add(spawner.getNextEnemy());
                }
            }
            if (!enemies.isEmpty()) {
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).update(clock.getElapsedTime());
                    if(enemies.get(i).getCurrentHealth() <=0) {
                        enemies.remove(i);
                        num = (int) (Math.random()* 2 + 1);
                        if (num == 1){
                            SoundPlayer.playSound("Explosion.wav");
                        }
                        else{
                            SoundPlayer.playSound("Explosion2.wav");
                        }
                    } else if(enemies.get(i).hasReachedEnd()) {
                        game.loseLife();
                        SoundPlayer.playSound("LifeLost.wav");
                        enemies.remove(i);
                    }
                }
            }
        }
    }
}
