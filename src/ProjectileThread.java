import java.util.LinkedList;

public class ProjectileThread extends Thread{
    private Projectile shot;
    public boolean active;

    public ProjectileThread(Projectile shot) {
        this.shot = shot;
        this.active = true;
    }

    public synchronized void run() {
        while(shot.isActive()) {
            shot.update();
        }
        if (!shot.isActive()){
            this.interrupt();
            return;
        }
    }

}
