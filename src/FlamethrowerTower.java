import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * FlamethrowerTower.java
 * A tower with unique properties that shoots enemies
 * @author Eric Ke, Kyle To
 * Last Updated: December 19 2019
 */
public class FlamethrowerTower extends Tower {

    public FlamethrowerTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setFireRate(0.05);
        setDamage(10);
        setRange(new Circle(x, y, 1000));
        setProjectileSpeed(1000);
        setProjectileExplosionRadius(0);
        setProjectileImagePath("resources/Projectiles/FlamethrowerBullet.png");
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/FlamethrowerBody.png"));
    }

    public void attack(double elapsedTime){
        //create an array of enemies within its range
        //fires at the enemy closest to base
        if (getAttackTime() <= 0 && !getWithin().isEmpty()) {
            setTarget(getWithin().getFirst());
            Projectile p =
                    new Projectile(
                            this, getProjectileImagePath(), getDamageType(), getDamage(), getProjectileSpeed(), getProjectileExplosionRadius(), getX(), getY(), getTargetX(), getTargetY(), getTarget()
                    );
            p.setBurn(new double[]{5, 25});
            getProjectiles().add(p);
            setAttackTime(getFireRate());
            SoundPlayer.playSound("Flamethrower.wav");
        }else {
            setAttackTime(getAttackTime() - elapsedTime);
        }
    }



}