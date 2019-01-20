import java.awt.*;

/**
 * RocketLauncherTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke
 * Last Updated: January 19 2019
 */
public class RocketLauncherTower extends RifleTower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
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
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/MissileLauncherBody.png"));
    }

}