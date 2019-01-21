import java.awt.*;

/**
 * FlamethrowerTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.(Images and sounds)
 * Last Updated: January 19 2019
 */
public class FlamethrowerTower extends Tower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public FlamethrowerTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setCost(1000);
        setFireRate(0.09);
        setDamage(10);
        setRange(new Circle(x, y, 300));
        setProjectileSpeed(1000);
        setProjectileExplosionRadius(0);
        setProjectileImagePath("resources/Projectiles/FlamethrowerBullet.png");
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/FlamethrowerBody.png"));
    }

    /**
     * attacks the target but also burns them because this is a flamethrower
     * @param elapsedTime time passed since last check
     */
    public void attack(double elapsedTime){
        //create an array of enemies within its range
        //fires at the enemy closest to base
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            setTarget(getWithin().getFirst());
            Projectile p =
                    new Projectile(
                            this, getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget()
                    );
            p.setBurn(new double[]{5, 25}); //this tower will apply a burn to the enemy
            getProjectiles().add(p);
            setAttackTime(getFireRate());
            SoundPlayer.playSound("Flamethrower.wav");
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }



}