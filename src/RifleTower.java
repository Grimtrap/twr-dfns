import java.awt.*;
import java.util.LinkedList;

public class RifleTower extends Tower {
    int count;
    private double fireRate;

    public RifleTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(50);
        setFireRate(0.5);
        setRange(new Circle(x, y, 1100));
        setProjectileImagePath("resources/Projectiles/StandardBullet.png");
        setProjectileSpeed(600);
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/BasicGunBody.png"));
        setClock(new Clock());
        count = 0;
    }

    @Override
    public void update(double timeElapsed) {
        findTargets();
        attack(timeElapsed);
        LinkedList<Projectile> projectiles = getProjectiles();
        for(int i = 0; i < projectiles.size(); i++) {
            Projectile current = projectiles.get(i);
            if(!current.isActive()) {
                projectiles.remove(i);
            } else {
                current.update(timeElapsed);
            }
        }
    }

    @Override
    public void attack(double elapsedTime) {
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            setTarget(getWithin().getFirst());
            Projectile p =
                    new Projectile(
                            this, getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget()
                    );
            getProjectiles().add(p);
            setAttackTime(getFireRate());
            SoundPlayer.playSound("BasicGun.wav");
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }
}