import java.util.LinkedList;
/**
 * TowersThread.java
 * Determines when towers can fire again
 * @author Kyle To
 * Last Updated: January 19 2019
 */
public class TowersThread extends Thread{

    private LinkedList<Tower> towers;
    private Clock clock;
    private TowersThread thisThread;
    private boolean running;

    /**
     * creates a thread which updates towers
     * @param towers towers in the game
     */
    public TowersThread(LinkedList<Tower> towers) {
        this.towers = towers;
        clock = new Clock();
        thisThread = this;
        running = true;
    }

    /**
     * runs the thread, which will update the towers in the game
     */
    public synchronized void run() {
        while(running) {
            clock.update();
            if (!towers.isEmpty()) {
                for (int i = 0; i < towers.size(); i++) {
                    towers.get(i).update(clock.getElapsedTime()); // this line sometimes throws a null pointer or index exception
                }
            }

        }
    }

    /**
     * stops the thread
     */
    public void stopThread() {
        this.running = false;
        thisThread = null;
    }

    /**
     * gets the towers in the game
     * @return the towers in the game
     */
    public LinkedList<Tower> getTowers() {
        return towers;
    }

    /**
     * sets the towers in the game
     * @param towers the towers in the game
     */
    public void setTowers(LinkedList<Tower> towers) {
        this.towers = towers;
    }
}
