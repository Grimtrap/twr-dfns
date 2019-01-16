import java.util.LinkedList;

public class ThreadTest extends Thread {
    private LinkedList<Enemy> enemies = new LinkedList<>();
    public boolean running;
    private Clock clock;
    private EnemySpawner spawner;
    private double duration;

    public ThreadTest(LinkedList<Enemy> enemies, EnemySpawner spawner) {
        this.enemies = enemies;
        this.clock = new Clock();
        this.running = true;
        this.spawner = spawner;
        duration = 0.5;
    }

    public void run() {
        while(true) {
            clock.update();
            duration -= clock.getElapsedTime();

            if(duration <= 0) {
                duration = 0.5;
                if(spawner.canSpawnMore()) {
                    enemies.add(spawner.getNextEnemy(0));
                }
            }
            if (!enemies.isEmpty()) {
                for (Enemy e : enemies) {
                    e.update(clock.getElapsedTime());
                }
            }

        }
    }
}
