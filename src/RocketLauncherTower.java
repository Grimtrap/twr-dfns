import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RocketLauncherTower.java
 * A tower with unique properties that shoots enemies
 * @author Eric Ke, Kyle To
 * Last Updated: December 19 2019
 */
public class RocketLauncherTower extends RifleTower {

    public RocketLauncherTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(150);
        setCost(400);
        setFireRate(2.5);
        setRange(new Circle(x, y, 400));
        setProjectileExplosionRadius(40);
        setProjectileImagePath("resources/Projectiles/Missile.png");
        setProjectileSpeed(5000);
        setSoundName("MissileLauncher.wav");
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/SAMMissileBody.png"));
    }

}