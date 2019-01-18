import java.util.LinkedList;

public class UpdaterThread extends Thread {
    private LinkedList<Enemy> enemies;
    public boolean running;
    private Clock clock;
    private EnemySpawner spawner;
    private double duration;
    private Game game;

    public UpdaterThread(LinkedList<Enemy> enemies, EnemySpawner spawner, Game game) {
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
                    enemies.add(spawner.getNextEnemy(0));
                }
            }
            if (!enemies.isEmpty()) {
                for (int i = 0; i < enemies.size(); i++) { //once again i fixed it by not using foreach LOL
                    enemies.get(i).update(clock.getElapsedTime());
                    if(enemies.get(i).getCurrentHealth() <=0) {
                        enemies.remove(i);
                    } else if(enemies.get(i).hasReachedEnd()) {
                        game.loseLife();
                        enemies.remove(i);
                    }
                }

            }

        }
    }
}
