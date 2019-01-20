import java.awt.*;

/**
 * AATower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.
 * Last Updated: January 19 2019
 */
public class AATower extends RifleTower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public AATower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(false);
        setAirTargeting(true);
        setDamage(50);
        setCost(250);
        setFireRate(0.1);
        setProjectileExplosionRadius(100);
        setRange(new Circle(x, y, 350));
        setProjectileSpeed(5000);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/AAGunBody.png"));
        setSoundName("AAGun.wav");
    }

}