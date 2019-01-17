import java.util.LinkedList;

public class TowersThread extends Thread{

    private LinkedList<Tower> towers = new LinkedList<>();
    public boolean running;
    private Clock clock;

    public TowersThread(LinkedList<Tower> towers) {
        this.towers = towers;
    }

    public synchronized void run() {
        while(running) {
            clock.update();

            if (!towers.isEmpty()) {
                for (Tower tower : towers) { //ay yo fix dis later lmao
                    //towers.update(clock.getElapsedTime());
                }
            }

        }
    }


}
