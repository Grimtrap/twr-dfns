import java.awt.*;

/**
 * RocketLauncherTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.(Images and sounds)
 * Last Updated: January 19 2019
 */
public class SAMTower extends RocketLauncherTower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public SAMTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(false);
        setAirTargeting(true);
        setProjectileImagePath("resources/Projectiles/Missile.png");
        setSoundName("SAMMissileLauncher.wav");
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/SAMMissileBody.png"));
    }

}