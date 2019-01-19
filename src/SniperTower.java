import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * SniperTower.java
 * A tower with unique properties that shoots enemies
 * @author Eric Ke, Kyle To
 * Last Updated: December 19 2019
 */
public class SniperTower extends RifleTower {

    public SniperTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(180);
        setDamageType(DamageTypes.PIERCE);
        setFireRate(3);
        setRange(new Circle(x, y, 800));
        setSoundName("SniperRifle.wav");
        setProjectileSpeed(3000);
        setProjectileImagePath("resources/Projectiles/SniperBullet.png");
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/SniperRifleBody.png"));
    }

}