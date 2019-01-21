import java.awt.*;
/**
 * MachineGunTower.java
 * A tower with unique properties that shoots enemies
 * @author Kyle To, Eric Ke, Michael T.(Images and sounds)
 * Last Updated: January 19 2019
 */
public class MachineGunTower extends RifleTower {

    /**
     * creates a new tower
     * @param x the tower's x position
     * @param y the tower's y position
     * @param game the game that the tower is in
     */
    public MachineGunTower(double x, double y, Game game) {
        super(x, y, game);
        setGroundTargeting(true);
        setAirTargeting(false);
        setDamage(25);
        setCost(250);
        setFireRate(0.1);
        setRange(new Circle(x, y, 290));
        setImage(Toolkit.getDefaultToolkit().getImage("resources/Towers/MachineGunBody.png"));
        setSoundName("MachineGun.wav");
        setProjectileImagePath("resources/Projectiles/MachineGunBullet.png");
    }

}