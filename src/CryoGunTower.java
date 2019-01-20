import java.awt.*;
import java.util.Random;
/**
 * CryoGunTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.
 * Last Updated: January 19 2019
 */
public class CryoGunTower extends Tower {
    private Random random;

    public CryoGunTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(10);
        setFireRate(0.5);
        setRange(new Circle(x, y, 500));
        setProjectileImagePath("resources/Projectiles/CryoBullet.png");
        //setDamageType();
        setProjectileSpeed(1000);
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/CryoGunBody.png"));
        random = new Random();
    }

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