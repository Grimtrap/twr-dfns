import java.util.LinkedList;

//DELETE THIS CLASS LATER
public class ProjectileThread extends Thread{
    private LinkedList<Projectile> projectiles;
    public boolean running;
    private Clock clock;

    public ProjectileThread(LinkedList<Projectile> projectiles) {
        this.projectiles = projectiles;
        running=true;
        clock = new Clock();
    }

    public synchronized void run() {
        while(running) {
            clock.update();
            if (!projectiles.isEmpty()) {
                for (int i = 0; i < projectiles.size(); i++) {
                    if(!projectiles.get(i).isActive()) {
                        projectiles.remove(i);
                    }
                    projectiles.get(i).update(3);
                }
            }

        }
    }

}
