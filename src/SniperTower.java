import java.awt.*;


/**
 * SniperTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.(Images and sounds)
 * Last Updated: January 19 2019
 */
public class SniperTower extends RifleTower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public SniperTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(true);
        setDamage(160);
        setCost(600);
        setDamageType(DamageTypes.PIERCE);
        setFireRate(2);
        setRange(new Circle(x, y, 600));
        setSoundName("SniperRifle.wav");
        setProjectileSpeed(3000);
        setProjectileImagePath("resources/Projectiles/SniperBullet.png");
        setProjectileExplosionRadius(0);
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/SniperRifleBody.png"));
    }

}