import java.awt.*;

/**
 * RocketLauncherTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.(Images and sounds)
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
        setCost(400);
        setDamage(100);
        setDamageType(DamageTypes.EXPLOSIVE);
        setFireRate(2);
        setProjectileExplosionRadius(150);
        setRange(new Circle(x, y, 300));
        setClock(new Clock());
        setProjectileImagePath("resources/Projectiles/Missile.png");
        setProjectileSpeed(1000);
        setSoundName("MissileLauncher.wav");
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/MissileLauncherBody.png"));
    }

}