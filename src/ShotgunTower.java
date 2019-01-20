import java.awt.*;
import java.util.Random;

/**
 * ShotgunTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke
 * Last Updated: January 19 2019
 */
public class ShotgunTower extends Tower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public ShotgunTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setProjectileImagePath("resources/Projectiles/ShotgunBullet.png");
        setDamage(60);
        setDamageType(DamageTypes.NORMAL);
        setFireRate(2);
        setRange(new Circle(x, y, 200));
        setProjectileSpeed(2500);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/ShotgunBody.png"));
    }

    /**
     * attacks the target(s) when the attack is available
     * @param elapsedTime time passed since last check
     */
    @Override
    public void attack(double elapsedTime){
        Random random = new Random();
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            for(int i = 0; i < 3; i++) {
                setTarget(getWithin().get(random.nextInt(getWithin().size())));
                Projectile p =
                        new Projectile(
                                this, getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget()
                        );
                getProjectiles().add(p);
            }
            setAttackTime(getFireRate());
            SoundPlayer.playSound("Shotgun.wav");
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }
}