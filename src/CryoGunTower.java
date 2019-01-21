import java.awt.*;
import java.util.Random;
/**
 * CryoGunTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.(Images and sounds)
 * Last Updated: January 19 2019
 */
public class CryoGunTower extends Tower {
    private Random random;

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public CryoGunTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(7);
        setFireRate(0.3);
        setRange(new Circle(x, y, 500));
        setCost(500);
        setProjectileImagePath("resources/Projectiles/CryoBullet.png");
        setDamageType(DamageTypes.NORMAL);
        setProjectileSpeed(1000);
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/CryoGunBody.png"));
        random = new Random();
    }

    /**
     * attacks the target
     * @param elapsedTime time passed since last check
     */
    @Override
    public void attack(double elapsedTime){
        //fires at random enemy to spread the slow
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            setTarget(getWithin().get(random.nextInt(getWithin().size())));
            Projectile p =
                    new Projectile(
                            this, getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget()
                    );
            p.setSlow(new double[]{5, 1});
            getProjectiles().add(p);
            setAttackTime(getFireRate());
            SoundPlayer.playSound("CryoGun.wav");
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }

}