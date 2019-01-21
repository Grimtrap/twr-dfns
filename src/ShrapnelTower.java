import java.awt.*;
import java.util.LinkedList;

/**
 * ShrapnelTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.(Images and sounds)
 * Last Updated: January 19 2019
 */
public class ShrapnelTower extends Tower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public ShrapnelTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(76);
        setCost(200);
        setFireRate(1.2);
        setProjectileSpeed(1500);
        setRange(new Circle(x, y, 100));
        setDamageType(DamageTypes.EXPLOSIVE);
        setProjectileImagePath("resources/Projectiles/ShrapnelBullet.png");
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/ShrapnelTower.png"));
    }

    /**
     * attacks the targets
     * @param elapsedTime time passed since last check
     */
    public void attack(double elapsedTime) {
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            for(int i = 0; i < getWithin().size(); i++) {
                setTarget(getWithin().get(i));
                Projectile p =
                        new Projectile(
                                this, getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget()
                        );
                getProjectiles().add(p);
            }
            setAttackTime(getFireRate());
            SoundPlayer.playSound("ShrapnelTower.wav");
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }
}