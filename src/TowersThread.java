import java.util.LinkedList;

public class TowersThread extends Thread{

    private LinkedList<Tower> towers;
    private LinkedList<Enemy> enemies;
    public boolean running;
    private Clock clock;

    public TowersThread(LinkedList<Tower> towers, LinkedList<Enemy> enemies) {
        this.towers = towers;
        this.enemies = enemies;
        this.running = true;
        clock = new Clock();
    }

    public synchronized void run() {
        while(running) {
            clock.update();
            if (!towers.isEmpty()) {
                for (Tower tower : towers) { //ay yo fix dis later lmao
                    tower.findTargets(enemies);
                    if (tower.getWithin().size() != 0) {
                        tower.attack(clock.elapsedTime);
                    }
                }
            }

        }
    }


}
