import java.awt.*;
import java.util.LinkedList;

/**
 * RifleTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.
 * Last Updated: January 19 2019
 */
public class RifleTower extends Tower {
    int count;
    private double fireRate;
    private String soundName;

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public RifleTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setCost(100);
        setDamage(50);
        setDamageType(DamageTypes.NORMAL);
        setSoundName("BasicGun.wav");
        setFireRate(1);
        setRange(new Circle(x, y, 400));
        setProjectileImagePath("resources/Projectiles/StandardBullet.png");
        setProjectileSpeed(600);
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/BasicGunBody.png"));
        setClock(new Clock());
        count = 0;
    }

    /**
     * sets the sound to play when shooting
     * @param soundLink path for the sound
     */
    public void setSoundName(String soundLink) {
        soundName = soundLink;
    }

    /**
     * updates the tower and performs several operations
     * @param timeElapsed time since last check
     */
    @Override
    public void update(double timeElapsed) {
        findTargets(); // found index exception
        attack(timeElapsed);
        LinkedList<Projectile> projectiles = getProjectiles();
        for(int i = 0; i < projectiles.size(); i++) {
            Projectile current = projectiles.get(i); // found null pointer exception
            if(!current.isActive()) {
                projectiles.remove(i); // found index or null pointer exception
            } else {
                current.update(timeElapsed);
            }
        }
    }

    /**
     * attacks the target
     * @param elapsedTime time passed since last check
     */
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
            SoundPlayer.playSound(soundName);
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }
}